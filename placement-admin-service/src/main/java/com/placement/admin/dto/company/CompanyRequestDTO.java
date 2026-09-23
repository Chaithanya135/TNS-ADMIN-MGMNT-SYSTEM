package com.placement.admin.dto.company;

import com.placement.admin.entity.enums.CompanyStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CompanyRequestDTO {

    @NotBlank(message = "Company name cannot be blank")
    @Size(max = 150, message = "Company name must not exceed 150 characters")
    private String companyName;

    @Email(message = "Email must be valid")
    @Size(max = 150, message = "Email must not exceed 150 characters")
    private String email;

    @Size(max = 20, message = "Phone must not exceed 20 characters")
    private String phone;

    @Size(max = 255, message = "Website must not exceed 255 characters")
    private String website;

    @Size(max = 100, message = "Industry must not exceed 100 characters")
    private String industry;

    @Size(max = 150, message = "Location must not exceed 150 characters")
    private String location;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    private CompanyStatus status;

    public CompanyRequestDTO() {
    }

    public CompanyRequestDTO(String companyName, String email, String phone, String website,
                             String industry, String location, String description, CompanyStatus status) {
        this.companyName = companyName;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.industry = industry;
        this.location = location;
        this.description = description;
        this.status = status;
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
}
