package app.statistics.application;

import app.statistics.infra.TaskMementoRepository;
import app.statistics.model.TaskMemento;
import app.statistics.model.enums.TaskType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ELLTaskManager {
    private final TaskMementoRepository taskMementoRepository;

    public void addTaskMemento(TaskMemento memento) {
        taskMementoRepository.save(memento);
    }

    public Optional<TaskMemento> findTaskMementoById(Long taskId) {
        return taskMementoRepository.findById(taskId);
    }

    public List<TaskMemento> findTaskMementosByTimestampAsc(TaskType taskType, LocalDateTime finishedAt) {
        return taskMementoRepository.findAllByTaskTypeAndFinishedAtAfter(taskType, finishedAt);
    }
}
