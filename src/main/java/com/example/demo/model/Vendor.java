package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vendors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String vendorName;
    private String contactEmail;
    private String address;
    private LocalDateTime createdAt;
    @ManyToMany(mappedBy = "favoriteVendors")
    private Set<User> users = new HashSet<>();
    @PrePersist protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }
}