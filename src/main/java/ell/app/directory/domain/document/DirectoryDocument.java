package ell.app.directory.domain.document;

import ell.app.shared.domain.document.MongoDocument;
import jakarta.annotation.Nullable;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document(collection = "directory")
public class DirectoryDocument implements MongoDocument {
    private final String id;
    private final String groupId;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Nullable
    private final String parentId; // root 디렉토리면 null

    public DirectoryDocument(String id, String groupId, String name, String parentId,
                             LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.parentId = parentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
