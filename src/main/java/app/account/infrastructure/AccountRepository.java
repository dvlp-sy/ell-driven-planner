package app.account.infrastructure;

import app.account.domain.document.AccountDocument;
import ell.planner.service.infrastructure.AdvancedMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends AdvancedMongoRepository<AccountDocument, String> {
}
