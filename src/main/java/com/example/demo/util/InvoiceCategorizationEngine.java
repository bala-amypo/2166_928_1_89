package com.example.demo.util;

import com.example.demo.model.*;

import java.util.Comparator;
import java.util.List;

public class InvoiceCategorizationEngine {

    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {
        return rules.stream()
                .filter(r -> match(invoice.getDescription(), r))
                .max(Comparator.comparing(CategorizationRule::getPriority))
                .map(CategorizationRule::getCategory)
                .orElse(null);
    }

    private boolean match(String desc, CategorizationRule rule) {
        if (desc == null) return false;

        return switch (rule.getMatchType()) {
            case EXACT -> desc.equalsIgnoreCase(rule.getKeyword());
            case CONTAINS -> desc.toLowerCase().contains(rule.getKeyword().toLowerCase());
            case REGEX -> desc.matches(rule.getKeyword());
        };
    }
}
