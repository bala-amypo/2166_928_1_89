package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"vendor_id", "invoiceNumber"})
    }
)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;

    private Double amount;

    private LocalDate invoiceDate;

    private String description;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private User uploadedBy;

    @ManyToOne
    private Category category;

    private LocalDateTime uploadedAt;

    @PrePersist
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getDescription() {          // ✅ REQUIRED
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {    // ✅ REQUIRED
        this.vendor = vendor;
    }

    public User getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(User uploadedBy) {   // ✅ REQUIRED
        this.uploadedBy = uploadedBy;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {   // ✅ REQUIRED
        this.category = category;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
}
