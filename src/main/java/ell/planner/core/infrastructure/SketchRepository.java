package ell.planner.core.infrastructure;

import ell.planner.core.domain.Sketch;
import ell.planner.service.domain.DataSourceType;

import java.util.Optional;

public interface SketchRepository {
    DataSourceType getType();
    void saveSketch(Sketch sketch);
    Optional<byte[]> findEllSketch(String collection, String field);
}
