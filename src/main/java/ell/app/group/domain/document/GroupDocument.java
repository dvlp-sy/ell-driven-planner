package ell.app.group.domain.document;

import ell.app.shared.domain.document.MongoDocument;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Document(collection = "group")
public class GroupDocument implements MongoDocument {
    @Id
    private final String id;
    private final String name;
    private final List<String> accountIds;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GroupDocument(String id, String name, List<String> accountIds,
                         LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.accountIds = accountIds;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
