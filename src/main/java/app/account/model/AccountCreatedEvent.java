package app.account.model;

import lombok.Getter;

@Getter
public class AccountCreatedEvent {
    private final Long accountId;
    private final String name;
    private final String email;
    private final String ipAddress;

    private AccountCreatedEvent(Long accountId, String name, String email, String ipAddress) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.ipAddress = ipAddress;
    }

    public static AccountCreatedEvent of(Long accountId, String name, String email, String ipAddress) {
        return new AccountCreatedEvent(accountId, name, email, ipAddress);
    }
}
