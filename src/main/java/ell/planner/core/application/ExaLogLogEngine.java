package ell.planner.core.application;

import ell.planner.core.infrastructure.SketchRepository;
import ell.planner.core.infrastructure.SketchRepositoryFactory;
import ell.planner.service.domain.DataSourceType;
import ell.planner.shared.domain.GroupBy;
import ell.planner.shared.domain.ParsedQuery;
import ell.planner.shared.domain.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExaLogLogEngine {

    private final long defaultTotalDocs = 1_000_000L; // fallback (replace with real count)
    private final SketchRepositoryFactory sketchRepositoryFactory;

    public Map<String, Double> estimate(DataSourceType dataSourceType, String collection, ParsedQuery query) {
        Map<String, Double> result = new HashMap<>();
        long totalDocs = getCollectionCount(collection);
        SketchRepository sketchRepository = sketchRepositoryFactory.getRepository(dataSourceType);


        for (Predicate p : query.getPredicates()) {
            Optional<byte[]> maybe = sketchRepository.findEllSketch(collection, p.getField());
            double selectivity;
            if (maybe.isPresent()) {
                byte[] sketchBytes = maybe.get();
                // TODO: replace with actual ExaLogLog deserialization
                org.dynatrace.exaloglog.ExaLogLog sketch = org.dynatrace.exaloglog.ExaLogLog.deserialize(sketchBytes);
                long ndv = sketch.estimate(); // estimated distinct values for field

                // simple heuristics:
                switch (p.getOperator()) {
                    case EQ:
                        // equality: 1 / NDV (if NDV==0 fallback)
                        selectivity = ndv > 0 ? (1.0 / Math.max(1.0, ndv)) : 1.0 / (double) Math.max(1, totalDocs);
                        break;
                    case IN:
                        // assume value list of size k
                        int k = (p.getValue() instanceof Collection<?>) ? ((Collection<?>) p.getValue()).size() : 1;
                        selectivity = Math.min(1.0, k * (ndv > 0 ? (1.0 / Math.max(1.0, ndv)) : 0.01));
                        break;
                    case GT:
                    case LT:
                    case GTE:
                    case LTE:
                        // ranges: use crude heuristic: assume uniform distribution across NDV
                        // NOTE: better approach: maintain quantile sketches or histogram
                        selectivity = 0.2; // fallback constant or derived from additional stats
                        break;
                    default:
                        selectivity = 0.5;
                }
            } else {
                // no sketch -> fall back to defaults
                selectivity = defaultSelectivityForOperator(p);
            }
            result.put(p.getField(), clamp(selectivity, 0.0, 1.0));
        }
        return result;
    }

    private long getCollectionCount(String collection) {
        // TODO: use MongoTemplate count query
        return defaultTotalDocs;
    }


    private double defaultSelectivityForOperator(Predicate p) {
        if ("$eq".equals(p.getOperator())) return 0.01; // conservative
        return 0.5;
    }


    private double clamp(double x, double lo, double hi) {
        return Math.max(lo, Math.min(hi, x));
    }


    private org.dynatrace.exaloglog.ExaLogLog createNewSketch() {
        // TODO: choose p/t parameters appropriate for your workload
        return org.dynatrace.exaloglog.ExaLogLog.create(12, 4);
    }
}
