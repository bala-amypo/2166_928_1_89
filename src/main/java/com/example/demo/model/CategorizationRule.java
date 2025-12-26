package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categorization_rules")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategorizationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotBlank
    private String keyword;

    @NotBlank
    private String matchType; // "EXACT", "CONTAINS", "REGEX"

    @Positive
    private Integer priority;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}