package ell.planner.core.domain;

import lombok.Getter;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document(collection = "sketch_meta")
public class MongoSketch implements Sketch {
    @Id
    private final ObjectId id;
    private final String collection;
    private final String field;
    private final Binary ellSketch;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public MongoSketch(ObjectId id, String collection, String field, Binary ellSketch,
                       LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = id;
        this.collection = collection;
        this.field = field;
        this.ellSketch = ellSketch;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
