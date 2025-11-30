package app.account.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    private String name;

    private String email;

    private Account(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static Account of(String name, String email) {
        return new Account(null, name, email);
    }
}
