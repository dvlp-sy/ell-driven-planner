package ell.planner.core.infrastructure;

import ell.planner.service.domain.DataSourceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SketchRepositoryFactory {
    private final List<SketchRepository> sketchRepositories;

    public SketchRepository getRepository(DataSourceType type) {
        return sketchRepositories.stream()
                .filter(repo -> repo.getType().equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No repository found for type: " + type));
    }
}
