package app.statistics.infra;

import app.statistics.model.AccountTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTaskRepository extends JpaRepository<AccountTask, Long> {
}
