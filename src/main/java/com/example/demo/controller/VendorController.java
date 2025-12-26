package com.example.demo.controller;

import com.example.demo.model.Vendor;
import com.example.demo.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
public class VendorController {
    private final VendorService vendorService;

    @PostMapping
    public ResponseEntity<Vendor> create(@RequestBody Vendor vendor) {
        return ResponseEntity.ok(vendorService.createVendor(vendor));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getOne(@PathVariable String id) {
        // Fix: Accept String -> Convert to Long
        return ResponseEntity.ok(vendorService.getVendor(Long.valueOf(id)));
    }
}