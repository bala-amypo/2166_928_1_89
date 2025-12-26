package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"vendor_id", "invoiceNumber"}
    )
)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;

    private Double amount;

    private String description;

    private LocalDate invoiceDate;

    private LocalDateTime uploadedAt;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User uploadedBy;

    @PrePersist
    public void onCreate() {
        this.uploadedAt = LocalDateTime.now();
    }

    // ===== REQUIRED METHODS FOR TESTS =====

    public void setId(long id) {          // 🔴 REQUIRED
        this.id = id;
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

    public String getDescription() {       // 🔴 REQUIRED
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setUploadedBy(User user) { // 🔴 REQUIRED
        this.uploadedBy = user;
    }

    public User getUploadedBy() {
        return uploadedBy;
    }

    public void setVendor(Vendor vendor) { // 🔴 REQUIRED
        this.vendor = vendor;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setCategory(Category category) { // 🔴 REQUIRED
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
}
