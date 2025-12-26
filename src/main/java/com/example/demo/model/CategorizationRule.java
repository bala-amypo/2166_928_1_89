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

    // REQUIRED
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    // ---------- GETTERS / SETTERS ----------
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    // STRING SUPPORT REQUIRED
    public void setMatchType(String type) {
        this.matchType = MatchType.valueOf(type);
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
