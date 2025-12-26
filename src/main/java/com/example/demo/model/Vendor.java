package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vendors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String vendorName;

    private String contactEmail;
    private String address;
    private LocalDate createdAt;

    @ManyToMany(mappedBy = "favoriteVendors")
    @Builder.Default
    private Set<User> users = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDate.now();
    }
}
