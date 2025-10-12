package ell.planner.service.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EllDrivenMongoRepositoryImpl<T, ID> implements EllDrivenMongoRepository<T, ID>  {

    private final MongoTemplate mongoTemplate;

    // ExaLogLog 기반의 쿼리 생성

}
