package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "invoices",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"vendor_id", "invoiceNumber"}
    )
)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    private String invoiceNumber;

    private Double amount;

    private LocalDate invoiceDate;

    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User uploadedBy;

    private LocalDateTime uploadedAt;

    public Invoice() {
    }

    @PrePersist
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
    }

    // ---------- getters ----------
    public Long getId() { return id; }
    public Vendor getVendor() { return vendor; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public Double getAmount() { return amount; }
    public LocalDate getInvoiceDate() { return invoiceDate; }
    public String getDescription() { return description; }
    public Category getCategory() { return category; }
    public User getUploadedBy() { return uploadedBy; }
    public LocalDateTime getUploadedAt() { return uploadedAt; }

    // ---------- setters ----------
    // ⚠️ REQUIRED BY TESTS
    public void setId(Long id) { this.id = id; }

    public void setVendor(Vendor vendor) { this.vendor = vendor; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setInvoiceDate(LocalDate invoiceDate) { this.invoiceDate = invoiceDate; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(Category category) { this.category = category; }
    public void setUploadedBy(User uploadedBy) { this.uploadedBy = uploadedBy; }
}
