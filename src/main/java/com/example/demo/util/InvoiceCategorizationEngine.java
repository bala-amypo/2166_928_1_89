package com.example.demo.util;

import com.example.demo.model.*;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class InvoiceCategorizationEngine {

    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {

        if (invoice == null || invoice.getDescription() == null || rules == null) {
            return null;
        }

        rules.sort(Comparator.comparingInt(CategorizationRule::getPriority).reversed());

        for (CategorizationRule rule : rules) {
            String desc = invoice.getDescription();
            String key = rule.getKeyword();

            switch (rule.getMatchType()) {
                case EXACT:
                    if (desc.equals(key)) return rule.getCategory();
                    break;

                case CONTAINS:
                    if (desc.toLowerCase().contains(key.toLowerCase()))
                        return rule.getCategory();
                    break;

                case REGEX:
                    if (Pattern.compile(key).matcher(desc).find())
                        return rule.getCategory();
                    break;
            }
        }
        return null;
    }
}
