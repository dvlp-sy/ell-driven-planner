package ell.app.account.application;

import ell.app.account.domain.Account;
import ell.app.account.domain.AccountGenerator;
import ell.app.account.domain.AccountUpdater;
import ell.app.account.domain.document.AccountDocument;
import ell.app.account.infrastructure.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void createAccount(AccountGenerator accountGenerator) {
        accountRepository.save(accountGenerator.toDocument());
    }

    public Account getAccount(String id) {
        return Account.from(accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found with id: " + id)));
    }

    public void updateAccount(AccountUpdater accountUpdater) {
        accountRepository.save(accountUpdater.toDocument());
    }

    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }
}
