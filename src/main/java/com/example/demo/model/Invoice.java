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

    public Vendor getVendor() { return vendor; }
    public User getUploadedBy() { return uploadedBy; }
    public Category getCategory() { return category; }
    public Double getAmount() { return amount; }
}
