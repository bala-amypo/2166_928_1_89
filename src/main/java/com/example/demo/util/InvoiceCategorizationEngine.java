package com.example.demo.util;

import com.example.demo.model.*;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class InvoiceCategorizationEngine {
    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {
        if (invoice.getDescription() == null || rules == null) return null;
        rules.sort((r1, r2) -> Integer.compare(r2.getPriority(), r1.getPriority()));
        for (CategorizationRule rule : rules) {
            String text = invoice.getDescription();
            String keyword = rule.getKeyword();
            boolean matched = false;
            if (rule.getMatchType() == MatchType.EXACT) matched = text.equals(keyword);
            else if (rule.getMatchType() == MatchType.CONTAINS) matched = text.toLowerCase().contains(keyword.toLowerCase());
            else if (rule.getMatchType() == MatchType.REGEX) {
                try { matched = Pattern.compile(keyword).matcher(text).find(); } catch (Exception ignored) {}
            }
            if (matched) return rule.getCategory();
        }
        return null;
    }
}