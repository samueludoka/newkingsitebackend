package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;

    @Enumerated(EnumType.STRING)
    private Country country;

    private String phoneNumber;

    private String password;

    private boolean active = true;
    @ElementCollection(fetch = EAGER)
    private Set<Role> roles;

    @OneToMany
    private List<Transaction> transactions;

    @OneToOne
    private Wallet wallet;

    @OneToOne
    private ForgotPassword forgotPassword;
}
