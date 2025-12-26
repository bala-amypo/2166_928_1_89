package com.example.demo.controller;

import com.example.demo.entity.Invoice;
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
    public ResponseEntity<Invoice> uploadInvoice(@PathVariable Long userId, 
                                                 @PathVariable Long vendorId, 
                                                 @RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.uploadInvoice(userId, vendorId, invoice));
    }

    @PostMapping("/categorize/{invoiceId}")
    public ResponseEntity<Invoice> categorizeInvoice(@PathVariable Long invoiceId) {
        return ResponseEntity.ok(invoiceService.categorizeInvoice(invoiceId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserInvoices(@PathVariable Long userId) {
        return ResponseEntity.ok(invoiceService.getInvoicesByUser(userId));
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Long invoiceId) {
        return ResponseEntity.ok(invoiceService.getInvoice(invoiceId));
    }
}