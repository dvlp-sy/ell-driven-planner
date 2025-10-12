package app.account.domain;

import app.account.domain.document.AccountDocument;
import app.shared.domain.document.MongoDocumentUpdater;

import java.time.LocalDateTime;
import java.util.List;

public class AccountUpdater implements MongoDocumentUpdater {
    private final String id;
    private final String name;
    private final String email;
    private final String password;
    private final List<String> groupIds;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private AccountUpdater(String id, String name, String email, String password,
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

    public static AccountUpdater ofWithPersonalInfo(Account account, String name, String email, String password) {
        return new AccountUpdater(account.getId(), name, email, password,
                account.getGroupIds(), account.getCreatedAt(), LocalDateTime.now());
    }

    public static AccountUpdater ofWithGroupIds(Account account, List<String> groupIds) {
        return new AccountUpdater(account.getId(), account.getName(), account.getEmail(), account.getPassword(),
                groupIds, account.getCreatedAt(), LocalDateTime.now());
    }

    @Override
    public AccountDocument toDocument() {
        return new AccountDocument(id, name, email, password, groupIds, createdAt, updatedAt);
    }
}
