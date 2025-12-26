package com.example.demo.util;

import com.example.demo.model.*;
import java.util.List;

public class InvoiceCategorizationEngine {

    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {
        for (CategorizationRule rule : rules) {
            String desc = invoice.getDescription();
            if (rule.getMatchType() == MatchType.EXACT && desc.equalsIgnoreCase(rule.getKeyword())) {
                return rule.getCategory();
            }
            if (rule.getMatchType() == MatchType.CONTAINS && desc.toLowerCase().contains(rule.getKeyword().toLowerCase())) {
                return rule.getCategory();
            }
            if (rule.getMatchType() == MatchType.REGEX && desc.matches(rule.getKeyword())) {
                return rule.getCategory();
            }
        }
        return null;
    }
}
