package com.example.demo.util;

import com.example.demo.model.Category;
import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Invoice;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class InvoiceCategorizationEngine {

    public Category determineCategory(
            Invoice invoice,
            List<CategorizationRule> rules
    ) {

        if (invoice == null || rules == null || rules.isEmpty()) {
            return null;
        }

        String description = invoice.getDescription();
        if (description == null) {
            return null;
        }

        return rules.stream()
                .sorted(Comparator.comparing(
                        CategorizationRule::getPriority
                ).reversed())
                .filter(rule -> matches(rule, description))
                .map(CategorizationRule::getCategory)
                .findFirst()
                .orElse(null);
    }

    private boolean matches(
            CategorizationRule rule,
            String description
    ) {

        if (rule.getKeyword() == null || rule.getMatchType() == null) {
            return false;
        }

        String keyword = rule.getKeyword();
        String type = rule.getMatchType().toUpperCase();

        switch (type) {

            case "EXACT":
                return description.equalsIgnoreCase(keyword);

            case "CONTAINS":
                return description.toLowerCase()
                        .contains(keyword.toLowerCase());

            case "REGEX":
                return Pattern.compile(keyword)
                        .matcher(description)
                        .find();

            default:
                return false;
        }
    }
}
