package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "invoices",
        uniqueConstraints = @UniqueConstraint(columnNames = {"vendor_id", "invoiceNumber"})
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @Column(nullable = false)
    private String invoiceNumber;

    @Column(nullable = false)
    private Double amount;

    private LocalDate invoiceDate;
    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User uploadedBy;

    private LocalDate uploadedAt;

    @PrePersist
    public void prePersist() {
        if (uploadedAt == null) uploadedAt = LocalDate.now();
    }
}
