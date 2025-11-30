package app.statistics.presentation;

import app.statistics.infra.TaskMementoRepository;
import app.statistics.model.TaskMemento;
import app.statistics.model.enums.TaskType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsApiController {

    private final TaskMementoRepository taskMementoRepository;

    @GetMapping
    public ResponseEntity<List<TaskMemento>> getTasks(TaskType taskType, LocalDateTime finishedAt) {
        List<TaskMemento> taskMementoList = taskMementoRepository
                .findAllByTaskTypeAndFinishedAtAfter(taskType, finishedAt);
        return ResponseEntity.ok(taskMementoList);
    }
}
