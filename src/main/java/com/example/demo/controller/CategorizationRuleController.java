package com.example.demo.controller;

import com.example.demo.model.CategorizationRule;
import com.example.demo.service.CategorizationRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rules")
@RequiredArgsConstructor
public class CategorizationRuleController {
    private final CategorizationRuleService ruleService;

    @PostMapping("/category/{categoryId}")
    public ResponseEntity<CategorizationRule> create(@PathVariable String categoryId, @RequestBody CategorizationRule rule) {
        // Fix: Parse String to Long
        return ResponseEntity.ok(ruleService.createRule(Long.valueOf(categoryId), rule));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getByCat(@PathVariable String categoryId) {
        // Fix: Parse String to Long
        return ResponseEntity.ok(ruleService.getRulesByCategory(Long.valueOf(categoryId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        // Fix: Parse String to Long
        ruleService.deleteRule(Long.valueOf(id));
        return ResponseEntity.noContent().build();
    }
}