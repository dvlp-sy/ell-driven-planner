package ell.app.group.domain;

import ell.app.group.domain.document.GroupDocument;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Group {
    private final String id;
    private final String name;
    private final List<String> accountIds;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private Group(String id, String name, List<String> accountIds,
                  LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.accountIds = accountIds;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Group from(GroupDocument groupDocument) {
        return new Group(groupDocument.getId(), groupDocument.getName(),
                groupDocument.getAccountIds(), groupDocument.getCreatedAt(), groupDocument.getUpdatedAt());
    }
}
