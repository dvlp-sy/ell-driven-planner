package app.account.presentation;

import app.account.application.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthApiController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(String email) {
        authService.signIn(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(String name, String email, String ipAddress) {
        authService.signUp(name, email, ipAddress);
        return ResponseEntity.ok().build();
    }
}
