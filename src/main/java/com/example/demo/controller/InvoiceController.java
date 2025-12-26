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

    // Change PathVariables to String
    @PostMapping("/upload/{userId}/{vendorId}")
    public ResponseEntity<Invoice> uploadInvoice(@PathVariable String userId, 
                                                 @PathVariable String vendorId, 
                                                 @RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.uploadInvoice(Long.valueOf(userId), Long.valueOf(vendorId), invoice));
    }

    // Change PathVariable to String
    @PostMapping("/categorize/{invoiceId}")
    public ResponseEntity<Invoice> categorizeInvoice(@PathVariable String invoiceId) {
        return ResponseEntity.ok(invoiceService.categorizeInvoice(Long.valueOf(invoiceId)));
    }

    // Change PathVariable to String
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserInvoices(@PathVariable String userId) {
        return ResponseEntity.ok(invoiceService.getInvoicesByUser(Long.valueOf(userId)));
    }

    // Change PathVariable to String
    @GetMapping("/{invoiceId}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable String invoiceId) {
        return ResponseEntity.ok(invoiceService.getInvoice(Long.valueOf(invoiceId)));
    }
}