package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @ManyToMany
    private Set<Vendor> favoriteVendors = new HashSet<>();

    private LocalDateTime createdAt;

    // ---------- REQUIRED BY TESTS ----------
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    // ---------- GETTERS / SETTERS ----------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // REQUIRED
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    // STRING SUPPORT REQUIRED BY TESTS
    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Vendor> getFavoriteVendors() {
        return favoriteVendors;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
