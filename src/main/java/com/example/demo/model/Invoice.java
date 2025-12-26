package com.example.demo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity @Table(name = "invoices", uniqueConstraints = {@UniqueConstraint(columnNames = {"vendor_id", "invoiceNumber"})})
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;
    @NotBlank
    private String invoiceNumber;
    @NotNull @Positive
    private Double amount;
    @NotNull
    private LocalDate invoiceDate;
    private String description;
    @ManyToOne @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne @JoinColumn(name = "uploaded_by_id", nullable = false)
    private User uploadedBy;
    private LocalDateTime uploadedAt;
    @PrePersist public void prePersist() { this.uploadedAt = LocalDateTime.now(); }
}