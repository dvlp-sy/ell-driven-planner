package ell.planner.core.domain;

import org.bson.types.Binary;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public interface Sketch {
    ObjectId getId();
    String getCollection();
    String getField();
    Binary getEllSketch();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
