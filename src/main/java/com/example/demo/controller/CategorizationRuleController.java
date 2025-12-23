package com.example.demo.controller;

import com.example.demo.model.CategorizationRule;
import com.example.demo.service.CategorizationRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
@CrossOrigin
public class CategorizationRuleController {

    private final CategorizationRuleService ruleService;

    public CategorizationRuleController(CategorizationRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/category/{categoryId}")
    public CategorizationRule createRule(
            @PathVariable Long categoryId,
            @RequestBody CategorizationRule rule) {

        return ruleService.createRule(categoryId, rule);
    }

    @GetMapping("/category/{categoryId}")
    public List<CategorizationRule> getRulesByCategory(@PathVariable Long categoryId) {
        return ruleService.getRulesByCategory(categoryId);
    }

    @DeleteMapping("/{ruleId}")
    public void deleteRule(@PathVariable Long ruleId) {
        ruleService.deleteRule(ruleId);
    }
}
