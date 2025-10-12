package app.directory.domain;

import app.directory.domain.document.DirectoryDocument;
import jakarta.annotation.Nullable;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Directory {
    private final String id;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Nullable
    private final String groupId;  // 개인 디렉토리면 null
    @Nullable
    private final String parentId; // root 디렉토리면 null

    private Directory(String id, String name, LocalDateTime createdAt, LocalDateTime updatedAt,
                      @Nullable String groupId, @Nullable String parentId) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.parentId = parentId;
    }

    public static Directory from(DirectoryDocument directoryDocument) {
        return new Directory(directoryDocument.getId(), directoryDocument.getName(), directoryDocument.getCreatedAt(),
                directoryDocument.getUpdatedAt(), directoryDocument.getGroupId(), directoryDocument.getParentId());
    }
}
