package app.directory.domain;

import app.directory.domain.document.DirectoryDocument;
import app.shared.domain.document.MongoDocumentGenerator;
import jakarta.annotation.Nullable;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.UUID;

public class DirectoryGenerator implements MongoDocumentGenerator {
    @Nullable
    private final String groupId;   // 개인 디렉토리면 null
    @Nullable
    private final String parentId;  // root 디렉토리면 null
    private final String name;

    private DirectoryGenerator(String groupId, String parentId, String name) {
        Assert.hasText(name, "Directory name must not be empty");
        this.groupId = groupId;
        this.parentId = parentId;
        this.name = name;
    }

    public static DirectoryGenerator of(String groupId, String parentId, String name) {
        return new DirectoryGenerator(groupId, parentId, name);
    }

    @Override
    public DirectoryDocument toDocument() {
        return new DirectoryDocument(UUID.randomUUID().toString(), groupId, name, parentId,
                LocalDateTime.now(), LocalDateTime.now());
    }
}
