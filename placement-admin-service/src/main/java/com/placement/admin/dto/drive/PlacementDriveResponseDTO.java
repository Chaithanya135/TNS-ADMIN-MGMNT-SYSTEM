package com.placement.admin.dto.drive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.placement.admin.entity.enums.DriveStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PlacementDriveResponseDTO {

    private Long id;
    private Long companyId;
    private String companyName;
    private String jobRole;
    private String jobDescription;
    private String eligibilityCriteria;
    private Double minimumCGPA;

    @JsonProperty("package")
    private Double packageAmount;

    private String location;
    private LocalDate driveDate;
    private LocalDate applicationDeadline;
    private DriveStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PlacementDriveResponseDTO() {
    }

    public PlacementDriveResponseDTO(Long id, Long companyId, String companyName, String jobRole,
                                     String jobDescription, String eligibilityCriteria, Double minimumCGPA,
                                     Double packageAmount, String location, LocalDate driveDate,
                                     LocalDate applicationDeadline, DriveStatus status,
                                     LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.companyId = companyId;
        this.companyName = companyName;
        this.jobRole = jobRole;
        this.jobDescription = jobDescription;
        this.eligibilityCriteria = eligibilityCriteria;
        this.minimumCGPA = minimumCGPA;
        this.packageAmount = packageAmount;
        this.location = location;
        this.driveDate = driveDate;
        this.applicationDeadline = applicationDeadline;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getEligibilityCriteria() {
        return eligibilityCriteria;
    }

    public void setEligibilityCriteria(String eligibilityCriteria) {
        this.eligibilityCriteria = eligibilityCriteria;
    }

    public Double getMinimumCGPA() {
        return minimumCGPA;
    }

    public void setMinimumCGPA(Double minimumCGPA) {
        this.minimumCGPA = minimumCGPA;
    }

    public Double getPackageAmount() {
        return packageAmount;
    }

    public void setPackageAmount(Double packageAmount) {
        this.packageAmount = packageAmount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDriveDate() {
        return driveDate;
    }

    public void setDriveDate(LocalDate driveDate) {
        this.driveDate = driveDate;
    }

    public LocalDate getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(LocalDate applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public DriveStatus getStatus() {
        return status;
    }

    public void setStatus(DriveStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
