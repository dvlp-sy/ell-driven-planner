package app.diary.domain.document;

import app.diary.domain.enums.DiaryPermission;
import app.shared.domain.document.MongoDocument;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document(collection = "diary")
public class DiaryDocument implements MongoDocument {
    @Id
    private final String id;
    private final String directoryId;
    private final String writerId;
    private final String title;
    private final String content;
    private final DiaryPermission permission;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public DiaryDocument(String id, String directoryId, String writerId, String title, String content,
                         DiaryPermission permission, LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = id;
        this.directoryId = directoryId;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.permission = permission;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
