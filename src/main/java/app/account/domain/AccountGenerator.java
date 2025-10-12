package app.account.domain;

import app.account.domain.document.AccountDocument;
import app.shared.domain.document.MongoDocumentGenerator;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

public class AccountGenerator implements MongoDocumentGenerator {
    private final String name;
    private final String email;
    private final String password;

    private AccountGenerator(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static AccountGenerator of(String name, String email, String password) {
        return new AccountGenerator(name, email, password);
    }

    @Override
    public AccountDocument toDocument() {
        return new AccountDocument(UUID.randomUUID().toString(), name, email, password,
                Collections.emptyList(), LocalDateTime.now(), LocalDateTime.now());
    }
}
