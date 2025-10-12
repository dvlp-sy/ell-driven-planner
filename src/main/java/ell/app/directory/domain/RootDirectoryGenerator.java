package ell.app.directory.domain;

import ell.app.directory.domain.document.DirectoryDocument;

import java.time.LocalDateTime;
import java.util.UUID;

public class RootDirectoryGenerator implements DirectoryGenerator {
    private final String groupId;
    private final String name;

    private RootDirectoryGenerator(String groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }

    public static RootDirectoryGenerator of(String groupId, String name) {
        return new RootDirectoryGenerator(groupId, name);
    }

    @Override
    public DirectoryDocument toDocument() {
        return new DirectoryDocument(UUID.randomUUID().toString(), groupId, name, null,
                LocalDateTime.now(), LocalDateTime.now());
    }
}
