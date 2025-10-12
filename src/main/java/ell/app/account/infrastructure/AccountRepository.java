package ell.app.account.infrastructure;

import ell.app.account.domain.document.AccountDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<AccountDocument, String> {
}
