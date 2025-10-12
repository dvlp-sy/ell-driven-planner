package ell.app.group.domain;

import ell.app.group.domain.document.GroupDocument;
import ell.app.shared.domain.document.MongoDocumentGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class GroupGenerator implements MongoDocumentGenerator {
    private final String name;
    private final String accountId;

    private GroupGenerator(String name, String accountId) {
        this.name = name;
        this.accountId = accountId;
    }

    public static GroupGenerator of(String name, String accountId) {
        return new GroupGenerator(name, accountId);
    }

    @Override
    public GroupDocument toDocument() {
        return new GroupDocument(UUID.randomUUID().toString(), name,
                List.of(accountId), LocalDateTime.now(), LocalDateTime.now());
    }
}
