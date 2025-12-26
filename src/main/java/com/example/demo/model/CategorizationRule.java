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

    // REQUIRED BY TESTS
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // STRING ACCEPTOR (REQUIRED)
    public void setMatchType(String type) {
        this.matchType = MatchType.valueOf(type);
    }

    public void setMatchType(MatchType type) {
        this.matchType = type;
    }

    // ===== GETTERS & SETTERS =====

    public MatchType getMatchType() {
        return matchType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPriority() {
        return priority;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
