package ell.app.directory.domain;

import ell.app.directory.domain.document.DirectoryDocument;

import java.time.LocalDateTime;
import java.util.UUID;

public class SubDirectoryGenerator implements DirectoryGenerator {
    private final String groupId;
    private final String parentId;
    private final String name;

    private SubDirectoryGenerator(String groupId, String parentId, String name) {
        this.groupId = groupId;
        this.parentId = parentId;
        this.name = name;
    }

    public static SubDirectoryGenerator of(String groupId, String parentId, String name) {
        return new SubDirectoryGenerator(groupId, parentId, name);
    }

    @Override
    public DirectoryDocument toDocument() {
        return new DirectoryDocument(UUID.randomUUID().toString(), groupId, name, parentId,
                LocalDateTime.now(), LocalDateTime.now());
    }
}
