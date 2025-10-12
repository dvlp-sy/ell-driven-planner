package ell.planner.service.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdvancedMongoRepository<T, ID> extends EllDrivenMongoRepository<T, ID>, MongoRepository<T, ID> {
}
