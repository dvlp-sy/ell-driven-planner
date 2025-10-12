package ell.app.diary.infrastructure;

import ell.app.diary.domain.document.DiaryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends MongoRepository<DiaryDocument, String> {
}
