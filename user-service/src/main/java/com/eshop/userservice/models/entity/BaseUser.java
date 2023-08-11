package com.eshop.userservice.models.entity;

import com.eshop.userservice.models.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@Table(name = "users")
public class BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateOfCreated;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Role role;

    @Column(name = "ENABLED", nullable = false)
    private Boolean enabled; // Это если надо будет бан( у меня есть готовая система бан-анбан), потом если что убрать

    @PrePersist
    protected void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public BaseUser() {
        super();
        this.enabled = true;
    }
}
