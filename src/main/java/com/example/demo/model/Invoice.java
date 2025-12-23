package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices", uniqueConstraints = {@UniqueConstraint(columnNames = {"vendor_id", "invoiceNumber"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "vendor_id")
    private Vendor vendor;
    @Column(nullable = false)
    private String invoiceNumber;
    @Column(nullable = false)
    private Double amount;
    private LocalDateTime invoiceDate;
    private String description;
    @ManyToOne @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(optional = false) @JoinColumn(name = "uploaded_by_id")
    private User uploadedBy;
    private LocalDateTime uploadedAt;
    @PrePersist protected void onCreate() {
        if (uploadedAt == null) uploadedAt = LocalDateTime.now();
    }
}