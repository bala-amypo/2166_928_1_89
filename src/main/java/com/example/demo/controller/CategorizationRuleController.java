package com.example.demo.controller;
import com.example.demo.model.CategorizationRule; // Corrected Import
import com.example.demo.service.CategorizationRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/rules") @RequiredArgsConstructor
public class CategorizationRuleController {
    private final CategorizationRuleService ruleService;
    @PostMapping("/category/{categoryId}")
    public ResponseEntity<CategorizationRule> create(@PathVariable Long categoryId, @RequestBody CategorizationRule rule) {
        return ResponseEntity.ok(ruleService.createRule(categoryId, rule));
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getByCat(@PathVariable Long categoryId) {
        return ResponseEntity.ok(ruleService.getRulesByCategory(categoryId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ruleService.deleteRule(id);
        return ResponseEntity.noContent().build();
    }
}