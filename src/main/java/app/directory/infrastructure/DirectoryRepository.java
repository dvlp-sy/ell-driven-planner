package app.directory.infrastructure;

import app.directory.domain.document.DirectoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepository extends MongoRepository<DirectoryDocument, String> {
}
