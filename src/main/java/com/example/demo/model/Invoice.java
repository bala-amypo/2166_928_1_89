package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
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

    // REQUIRED BY TESTS
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
    }

    // REQUIRED
    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setUploadedBy(User user) {
        this.uploadedBy = user;
    }
}
