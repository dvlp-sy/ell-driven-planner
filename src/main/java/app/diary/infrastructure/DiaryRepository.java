package app.diary.infrastructure;

import app.diary.domain.document.DiaryDocument;
import ell.planner.service.infrastructure.AdvancedMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends AdvancedMongoRepository<DiaryDocument, String> {
}
