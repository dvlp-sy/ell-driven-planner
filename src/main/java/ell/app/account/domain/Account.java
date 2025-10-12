package ell.app.account.domain;

import ell.app.account.domain.document.AccountDocument;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Account {
    private final String id;
    private final String name;
    private final String email;
    private final String password;
    private final List<String> groupIds;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private Account(String id, String name, String email, String password,
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

    public static Account from(AccountDocument accountDocument) {
        return new Account(accountDocument.getId(),  accountDocument.getName(),
                accountDocument.getEmail(), accountDocument.getPassword(), accountDocument.getGroupIds(),
                accountDocument.getCreatedAt(), accountDocument.getUpdatedAt());
    }
}
