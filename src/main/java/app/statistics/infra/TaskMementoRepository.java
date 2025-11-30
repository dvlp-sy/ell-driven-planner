package app.statistics.infra;

import app.statistics.model.TaskMemento;
import app.statistics.model.enums.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskMementoRepository extends JpaRepository<TaskMemento, Long> {
    List<TaskMemento> findAllByTaskTypeAndFinishedAtAfter(TaskType taskType, LocalDateTime finishedAt);
}
