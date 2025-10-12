package ell.planner.shared.domain;

import ell.planner.shared.domain.enums.Operator;
import ell.planner.shared.domain.enums.SortOrder;
import jakarta.annotation.Nullable;
import lombok.Getter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
public class ParsedQuery {
    private final List<Predicate> predicates;
    private final List<GroupBy> groupBys;
    private final List<SortBy> sortBys;

    private ParsedQuery(List<Predicate> predicates, List<GroupBy> groupBys, List<SortBy> sortBys) {
        this.predicates = predicates;
        this.groupBys = groupBys;
        this.sortBys = sortBys;
    }

    public static ParsedQuery fromBson(Document queryDoc) {
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> entry : queryDoc.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Document doc) {
                for (Map.Entry<String, Object> opEntry : doc.entrySet()) {
                    String op = opEntry.getKey();
                    Object opValue = opEntry.getValue();
                    predicates.add(Predicate.of(field, mongoOpToEnum(op), opValue));
                }
            } else {
                predicates.add(Predicate.of(field, Operator.EQ, value));
            }
        }

        return new ParsedQuery(predicates, Collections.emptyList(), Collections.emptyList());
    }

    public static ParsedQuery fromAggregation(List<Document> pipeline) {
        List<Predicate> predicates = new ArrayList<>();
        List<GroupBy> groupBys = new ArrayList<>();
        List<SortBy> sortBys = new ArrayList<>();

        for (Document stage : pipeline) {
            String stageKey = stage.keySet().iterator().next();
            Document content = (Document) stage.get(stageKey);

            switch (stageKey) {
                case "$match" -> predicates.addAll(parseMatch(content));
                case "$group" -> groupBys.add(parseGroup(content));
                case "$sort"  -> sortBys.addAll(parseSort(content));
            }
        }

        return new ParsedQuery(predicates, groupBys, sortBys);
    }

    private static List<Predicate> parseMatch(Document matchDoc) {
        List<Predicate> preds = new ArrayList<>();
        for (var e : matchDoc.entrySet()) {
            if (e.getValue() instanceof Document doc) {
                for (var op : doc.entrySet()) {
                    preds.add(Predicate.of(e.getKey(), mongoOpToEnum(op.getKey()), op.getValue()));
                }
            } else {
                preds.add(Predicate.of(e.getKey(), Operator.EQ, e.getValue()));
            }
        }
        return preds;
    }

    private static GroupBy parseGroup(Document groupDoc) {
        Object id = groupDoc.get("_id");
        if (id instanceof Document doc) {
            List<String> fields = doc.values().stream()
                    .map(Object::toString)
                    .map(v -> v.startsWith("$") ? v.substring(1) : v)
                    .toList();
            return GroupBy.from(fields);
        }
        if (id instanceof String s && s.startsWith("$")) {
            return GroupBy.from(List.of(s.substring(1)));
        }
        return GroupBy.from(Collections.emptyList());
    }

    private static List<SortBy> parseSort(Document sortDoc) {
        return sortDoc.entrySet().stream()
                .map(e -> SortBy.of(e.getKey(), ((int) e.getValue()) > 0 ? SortOrder.ASC : SortOrder.DESC))
                .toList();
    }

    private static Operator mongoOpToEnum(String mongoOp) {
        return switch (mongoOp) {
            case "$eq" -> Operator.EQ;
            case "$gt" -> Operator.GT;
            case "$gte" -> Operator.GTE;
            case "$lt" -> Operator.LT;
            case "$lte" -> Operator.LTE;
            case "$ne" -> Operator.NE;
            case "$in" -> Operator.IN;
            default -> Operator.ETC;
        };
    }
}
