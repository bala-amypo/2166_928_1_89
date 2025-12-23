package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String role;
    private LocalDateTime createdAt;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_favorite_vendors",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "vendor_id"))
    private Set<Vendor> favoriteVendors = new HashSet<>();
    @PrePersist protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (role == null) role = "USER";
    }
}