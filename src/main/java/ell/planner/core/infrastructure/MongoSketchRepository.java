package ell.planner.core.infrastructure;

import ell.planner.core.domain.MongoSketch;
import ell.planner.core.domain.Sketch;
import ell.planner.service.domain.DataSourceType;
import lombok.RequiredArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MongoSketchRepository implements SketchRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public DataSourceType getType() {
        return DataSourceType.MONGO;
    }

    @Override
    public void saveSketch(Sketch sketch) {
        validate(sketch);
        Query query = new Query(Criteria.where("_id").is(sketch.getId()));
        Update update = new Update()
                .set("collection", sketch.getCollection())
                .set("field", sketch.getField())
                .set("ellSketch", sketch.getEllSketch())
                .set("updatedAt", sketch.getUpdatedAt());
        mongoTemplate.upsert(query, update, Sketch.class);
    }

    @Override
    public Optional<byte[]> findEllSketch(String collection, String field) {
        Query query = new Query(Criteria
                .where("collection").is(collection)
                .and("field").is(field));
        Sketch sketch = mongoTemplate.findOne(query, Sketch.class);
        validate(sketch);
        return Optional.ofNullable(sketch.getEllSketch().getData());
    }

    private void validate(Sketch sketch) {
        if (!(sketch instanceof MongoSketch mongoSketch)) {
            throw new IllegalArgumentException("Invalid sketch type");
        }
    }
}
