package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "categorization_rules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Category category;

    @Column(nullable = false)
    private String keyword;

    @Enumerated(EnumType.STRING)
    private MatchType matchType;

    private int priority;
    private LocalDate createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDate.now();
    }

    // REQUIRED BY TESTS
    public void setMatchType(String value) {
        this.matchType = MatchType.fromString(value);
    }
}
