package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String categoryName;

    private String description;
    private LocalDate createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDate.now();
    }
}
