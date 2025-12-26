package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    @Enumerated(EnumType.STRING)
    private MatchType matchType;

    private Integer priority;

    @ManyToOne
    private Category category;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // ===== REQUIRED GETTERS / SETTERS =====

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public String getKeyword() {
        return keyword;
    }
}
