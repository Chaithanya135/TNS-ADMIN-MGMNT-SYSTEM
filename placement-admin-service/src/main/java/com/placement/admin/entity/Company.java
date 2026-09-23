package com.placement.admin.entity;

import com.placement.admin.entity.enums.CompanyStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false, length = 150)
    private String companyName;

    @Column(length = 150)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(length = 255)
    private String website;

    @Column(length = 100)
    private String industry;

    @Column(length = 150)
    private String location;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CompanyStatus status = CompanyStatus.ACTIVE;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlacementDrive> placementDrives = new ArrayList<>();

    public Company() {
    }

    public Company(Long id, String companyName, String email, String phone, String website,
                   String industry, String location, String description, CompanyStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.companyName = companyName;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.industry = industry;
        this.location = location;
        this.description = description;
        this.status = status != null ? status : CompanyStatus.ACTIVE;
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = CompanyStatus.ACTIVE;
        }
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CompanyStatus getStatus() {
        return status;
    }

    public void setStatus(CompanyStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<PlacementDrive> getPlacementDrives() {
        return placementDrives;
    }

    public void setPlacementDrives(List<PlacementDrive> placementDrives) {
        this.placementDrives = placementDrives;
    }
}
