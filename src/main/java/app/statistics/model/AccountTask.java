package app.statistics.model;

import app.statistics.model.enums.TaskStatus;
import app.statistics.model.enums.TaskType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    private TaskType type;

    private TaskStatus status;

    private String data;

    private AccountTask(Long id, TaskType type, TaskStatus status, String data) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.data = data;
    }

    public static AccountTask of(TaskType type, TaskStatus status, String data) {
        return new AccountTask(null, type, status, data);
    }
}
