package app.group.domain;

import app.group.domain.document.GroupDocument;
import app.shared.domain.document.MongoDocumentUpdater;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupUpdater implements MongoDocumentUpdater {
    private final String id;
    private final String name;
    private final List<String> accountIds;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private GroupUpdater(String id, String name, List<String> accountIds,
                        LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.accountIds = accountIds;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static GroupUpdater ofWithName(Group group, String name) {
        return new GroupUpdater(group.getId(), name, group.getAccountIds(),
                group.getCreatedAt(), LocalDateTime.now());
    }

    public static GroupUpdater ofWithAccount(Group group, List<String> accountIds) {
        return new GroupUpdater(group.getId(), group.getName(),
                Stream.concat(group.getAccountIds().stream(), accountIds.stream()).collect(Collectors.toList()),
                group.getCreatedAt(), LocalDateTime.now());
    }

    @Override
    public GroupDocument toDocument() {
        return new GroupDocument(id, name, accountIds, createdAt, updatedAt);
    }
}
