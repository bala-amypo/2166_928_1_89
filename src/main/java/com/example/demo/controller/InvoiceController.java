package com.example.demo.controller;

import com.example.demo.model.Invoice;
import com.example.demo.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping("/upload/{userId}/{vendorId}")
    public ResponseEntity<Invoice> uploadInvoice(@PathVariable String userId, 
                                                 @PathVariable String vendorId, 
                                                 @RequestBody Invoice invoice) {
        // Fix: Accept String -> Convert to Long
        return ResponseEntity.ok(invoiceService.uploadInvoice(Long.valueOf(userId), Long.valueOf(vendorId), invoice));
    }

    @PostMapping("/categorize/{invoiceId}")
    public ResponseEntity<Invoice> categorizeInvoice(@PathVariable String invoiceId) {
        // Fix: Accept String -> Convert to Long
        return ResponseEntity.ok(invoiceService.categorizeInvoice(Long.valueOf(invoiceId)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserInvoices(@PathVariable String userId) {
        // Fix: Accept String -> Convert to Long
        return ResponseEntity.ok(invoiceService.getInvoicesByUser(Long.valueOf(userId)));
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable String invoiceId) {
        // Fix: Accept String -> Convert to Long
        return ResponseEntity.ok(invoiceService.getInvoice(Long.valueOf(invoiceId)));
    }
}