package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    name = "vendors",
    uniqueConstraints = @UniqueConstraint(columnNames = "vendorName")
)
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vendorName;

    private String contactEmail;

    private String address;

    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "favoriteVendors")
    private Set<User> users = new HashSet<>();

    public Vendor() {}

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getVendorName() { return vendorName; }
    public String getContactEmail() { return contactEmail; }
    public String getAddress() { return address; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Set<User> getUsers() { return users; }

    public void setId(Long id) { this.id = id; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public void setAddress(String address) { this.address = address; }
}
