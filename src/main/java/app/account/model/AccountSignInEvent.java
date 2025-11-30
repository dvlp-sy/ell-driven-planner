package app.account.model;

import lombok.Getter;

@Getter
public class AccountSignInEvent {
    private final Long accountId;

    private AccountSignInEvent(Long accountId) {
        this.accountId = accountId;
    }

    public static AccountSignInEvent of(Long accountId) {
        return new AccountSignInEvent(accountId);
    }
}
