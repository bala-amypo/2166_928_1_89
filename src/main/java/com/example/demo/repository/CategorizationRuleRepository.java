package com.example.demo.repository;

import com.example.demo.entity.CategorizationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CategorizationRuleRepository extends JpaRepository<CategorizationRule, Long> {
    // Note: The SRS asks for this method name but describes retrieving rules by Category or matching.
    // We implement the one that returns rules for the engine.
    List<CategorizationRule> findAllByOrderByPriorityDesc();

    // Query for category specific lookup
    List<CategorizationRule> findByCategoryIdOrderByPriorityDesc(Long categoryId);
    
    // As per SRS Query Implementation section
    @Query("SELECT r FROM CategorizationRule r WHERE r.category.id = :categoryId ORDER BY r.priority DESC")
    List<CategorizationRule> findMatchingRulesByDescription(@Param("categoryId") Long categoryId);
}