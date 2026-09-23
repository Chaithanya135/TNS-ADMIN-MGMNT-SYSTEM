package com.placement.admin.dto.company;

import com.placement.admin.entity.enums.CompanyStatus;
import java.time.LocalDateTime;

public class CompanyResponseDTO {

    private Long id;
    private String companyName;
    private String email;
    private String phone;
    private String website;
    private String industry;
    private String location;
    private String description;
    private CompanyStatus status;
    private LocalDateTime createdAt;

    public CompanyResponseDTO() {
    }

    public CompanyResponseDTO(Long id, String companyName, String email, String phone,
                              String website, String industry, String location,
                              String description, CompanyStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.companyName = companyName;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.industry = industry;
        this.location = location;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

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
}
