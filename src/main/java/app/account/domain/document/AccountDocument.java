package app.account.domain.document;

import app.shared.domain.document.MongoDocument;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Document(collection = "account")
public class AccountDocument implements MongoDocument {
    @Id
    private final String id;
    private final String name;
    private final String email;
    private final String password;
    private final List<String> groupIds;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public AccountDocument(String id, String name, String email, String password,
                           List<String> groupIds, LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.groupIds = groupIds;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
