package com.example.demo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;
@Entity @Table(name = "categories") @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(unique = true)
    private String categoryName;
    private String description;
    private LocalDateTime createdAt;
    @PrePersist public void prePersist() { this.createdAt = LocalDateTime.now(); }
}