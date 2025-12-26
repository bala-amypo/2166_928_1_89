package com.example.demo.service.impl;
import com.example.demo.model.*; // Corrected Import
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.InvoiceService;
import com.example.demo.util.InvoiceCategorizationEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service @Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;
    private final VendorRepository vendorRepository;
    private final CategoryRepository categoryRepository;
    private final CategorizationRuleRepository ruleRepository;
    private final InvoiceCategorizationEngine categorizationEngine;
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, UserRepository userRepository, VendorRepository vendorRepository, CategoryRepository categoryRepository, CategorizationRuleRepository ruleRepository, InvoiceCategorizationEngine categorizationEngine) {
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
        this.categoryRepository = categoryRepository;
        this.ruleRepository = ruleRepository;
        this.categorizationEngine = categorizationEngine;
    }
    public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
        if (invoice.getAmount() <= 0) throw new IllegalArgumentException("Amount must be positive");
        invoice.setUploadedBy(user);
        invoice.setVendor(vendor);
        invoice.setCategory(null);
        return invoiceRepository.save(invoice);
    }
    public Invoice categorizeInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));
        List<CategorizationRule> rules = ruleRepository.findAllByOrderByPriorityDesc();
        invoice.setCategory(categorizationEngine.determineCategory(invoice, rules));
        return invoiceRepository.save(invoice);
    }
    public List<Invoice> getInvoicesByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return invoiceRepository.findByUploadedBy(user);
    }
    public Invoice getInvoice(Long invoiceId) { return invoiceRepository.findById(invoiceId).orElseThrow(() -> new ResourceNotFoundException("Invoice not found")); }
}