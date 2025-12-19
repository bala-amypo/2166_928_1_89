package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"vendor_id", "invoiceNumber"})
)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    private String invoiceNumber;
    private Double amount;
    private LocalDateTime invoiceDate;
    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User uploadedBy;

    private LocalDateTime uploadedAt;

    @PrePersist
    void prePersist() {
        this.uploadedAt = LocalDateTime.now();
    }

    // getters & setters
}
