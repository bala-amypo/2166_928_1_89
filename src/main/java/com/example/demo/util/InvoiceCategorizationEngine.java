package com.example.demo.util;

import com.example.demo.model.*;
import java.util.*;
import java.util.regex.Pattern;

public class InvoiceCategorizationEngine {

    public Category determineCategory(
            Invoice invoice,
            List<CategorizationRule> rules) {

        if (rules == null || rules.isEmpty()) return null;

        rules.sort((a, b) -> b.getPriority() - a.getPriority());

        for (CategorizationRule rule : rules) {
            String desc = invoice.getDescription();
            String key = rule.getKeyword();

            switch (rule.getMatchType()) {
                case "EXACT":
                    if (desc.equals(key)) return rule.getCategory();
                    break;
                case "CONTAINS":
                    if (desc.toLowerCase().contains(key.toLowerCase()))
                        return rule.getCategory();
                    break;
                case "REGEX":
                    if (Pattern.compile(key).matcher(desc).find())
                        return rule.getCategory();
            }
        }
        return null;
    }
}
