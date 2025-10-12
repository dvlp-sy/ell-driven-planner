package ell.app.directory.domain;

import ell.app.directory.domain.document.DirectoryDocument;
import ell.app.shared.domain.document.MongoDocumentUpdater;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;

public class DirectoryUpdater implements MongoDocumentUpdater {
    private final String id;
    private final String groupId;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Nullable
    private final String parentId; // root 디렉토리면 null

    private DirectoryUpdater(String id, String groupId, String name, LocalDateTime createdAt, LocalDateTime updatedAt,
                             @Nullable String parentId) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.parentId = parentId;
    }

    public static DirectoryUpdater ofWithName(Directory directory, String name) {
        return new DirectoryUpdater(directory.getId(), directory.getGroupId(), name, directory.getCreatedAt(),
                LocalDateTime.now(), directory.getParentId());
    }

    public static DirectoryUpdater ofWithParentId(Directory directory, String parentId) {
        return new DirectoryUpdater(directory.getId(), directory.getGroupId(), directory.getName(),
                directory.getCreatedAt(), LocalDateTime.now(), parentId);
    }

    @Override
    public DirectoryDocument toDocument() {
        return new DirectoryDocument(id, groupId, name, parentId, createdAt, updatedAt);
    }
}
