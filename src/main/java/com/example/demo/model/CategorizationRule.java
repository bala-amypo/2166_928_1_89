package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categorization_rules")
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Category category;

    private String keyword;

    // ⚠️ MUST BE STRING (tests pass String values)
    private String matchType;

    private Integer priority;

    private LocalDateTime createdAt;

    public CategorizationRule() {
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // ---------- getters ----------
    public Long getId() { return id; }
    public Category getCategory() { return category; }
    public String getKeyword() { return keyword; }
    public String getMatchType() { return matchType; }
    public Integer getPriority() { return priority; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // ---------- setters ----------
    public void setCategory(Category category) { this.category = category; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public void setMatchType(String matchType) { this.matchType = matchType; }
    public void setPriority(Integer priority) { this.priority = priority; }
}
