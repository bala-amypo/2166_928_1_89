package com.example.demo.util;

import com.example.demo.model.*;
import java.util.List;

public class InvoiceCategorizationEngine {

    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {

        String description = invoice.getDescription();

        for (CategorizationRule rule : rules) {

            if (rule.getMatchType() == MatchType.EXACT &&
                description.equalsIgnoreCase(rule.getKeyword())) {
                return rule.getCategory();
            }

            if (rule.getMatchType() == MatchType.CONTAINS &&
                description.toLowerCase().contains(rule.getKeyword().toLowerCase())) {
                return rule.getCategory();
            }

            if (rule.getMatchType() == MatchType.REGEX &&
                description.matches(rule.getKeyword())) {
                return rule.getCategory();
            }
        }
        return null;
    }
}
