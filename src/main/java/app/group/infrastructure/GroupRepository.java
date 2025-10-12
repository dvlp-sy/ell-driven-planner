package app.group.infrastructure;

import app.group.domain.document.GroupDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends MongoRepository<GroupDocument, String> {
}
