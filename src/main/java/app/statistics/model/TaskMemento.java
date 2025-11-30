package app.statistics.model;

import app.statistics.model.enums.TaskType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskMemento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private String id;

    @Column
    private TaskType taskType;

    @Column
    private LocalDateTime finishedAt;

    @Column
    private double distinctCount;

    private TaskMemento(TaskType taskType, LocalDateTime finishedAt, double distinctCount) {
        this.taskType = taskType;
        this.finishedAt = finishedAt;
        this.distinctCount = distinctCount;
    }

    public static TaskMemento of(TaskType taskType, LocalDateTime finishedAt, double distinctCount) {
        return new TaskMemento(taskType, finishedAt, distinctCount);
    }

    @Override
    public String toString() {
        return String.format("AccountTaskMemento(finishedAt(%s), distinctCount=(%.6f))", finishedAt, distinctCount);
    }
}
