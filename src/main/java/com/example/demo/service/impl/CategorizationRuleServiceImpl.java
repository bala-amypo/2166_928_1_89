package com.example.demo.service.impl;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.CategorizationRuleService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategorizationRuleServiceImpl implements CategorizationRuleService {
    private final CategorizationRuleRepository ruleRepository;
    private final CategoryRepository categoryRepository;

    public CategorizationRuleServiceImpl(CategorizationRuleRepository ruleRepository, CategoryRepository categoryRepository) {
        this.ruleRepository = ruleRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public CategorizationRule createRule(Long categoryId, CategorizationRule rule) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        rule.setCategory(category);
        return ruleRepository.save(rule);
    }
    @Override
    public List<CategorizationRule> getRulesByCategory(Long categoryId) {
        return ruleRepository.findAll().stream().filter(r -> r.getCategory().getId().equals(categoryId)).collect(Collectors.toList());
    }
    @Override
    public void deleteRule(Long ruleId) { ruleRepository.deleteById(ruleId); }
}