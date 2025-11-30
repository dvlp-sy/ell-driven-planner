package app.account.application;

import app.account.infra.AccountRepository;
import app.account.model.Account;
import app.account.model.AccountCreatedEvent;
import app.account.model.AccountSignInEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    private final ApplicationEventPublisher eventPublisher;

    public void signUp(String name, String email, String ipAddress) {
        Account account = accountRepository.save(Account.of(name, email));
        eventPublisher.publishEvent(AccountCreatedEvent.of(account.getId(), name, email, ipAddress));
    }

    public void signIn(String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("No account found with email: " + email));
        eventPublisher.publishEvent(AccountSignInEvent.of(account.getId()));
    }
}
