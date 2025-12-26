package com.example.demo.util;
import com.example.demo.model.CategorizationRule; // Corrected Import
import com.example.demo.model.Category; // Corrected Import
import com.example.demo.model.Invoice; // Corrected Import
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.regex.Pattern;
@Component
public class InvoiceCategorizationEngine {
    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {
        if (invoice.getDescription() == null) return null;
        for (CategorizationRule rule : rules) {
            boolean match = false;
            String desc = invoice.getDescription();
            String keyword = rule.getKeyword();
            switch (rule.getMatchType().toUpperCase()) {
                case "EXACT": match = desc.equalsIgnoreCase(keyword); break;
                case "CONTAINS": match = desc.toLowerCase().contains(keyword.toLowerCase()); break;
                case "REGEX": match = Pattern.compile(keyword).matcher(desc).find(); break;
            }
            if (match) return rule.getCategory();
        }
        return null;
    }
}