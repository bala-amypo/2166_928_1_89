package com.example.demo.repository;
import com.example.demo.entity.CategorizationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface CategorizationRuleRepository extends JpaRepository<CategorizationRule, Long> {
    List<CategorizationRule> findAllByOrderByPriorityDesc();
    List<CategorizationRule> findByCategoryIdOrderByPriorityDesc(Long categoryId);
    @Query("SELECT r FROM CategorizationRule r WHERE r.category.id = :categoryId ORDER BY r.priority DESC")
    List<CategorizationRule> findMatchingRulesByDescription(@Param("categoryId") Long categoryId);
}