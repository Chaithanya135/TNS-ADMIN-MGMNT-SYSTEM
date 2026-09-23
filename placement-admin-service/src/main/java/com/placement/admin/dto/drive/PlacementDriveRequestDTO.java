package com.placement.admin.dto.drive;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.placement.admin.entity.enums.DriveStatus;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class PlacementDriveRequestDTO {

    @NotNull(message = "Company ID cannot be null")
    private Long companyId;

    @NotBlank(message = "Job role cannot be blank")
    @Size(max = 100, message = "Job role must not exceed 100 characters")
    private String jobRole;

    @Size(max = 2000, message = "Job description must not exceed 2000 characters")
    private String jobDescription;

    @Size(max = 500, message = "Eligibility criteria must not exceed 500 characters")
    private String eligibilityCriteria;

    @NotNull(message = "Minimum CGPA cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum CGPA must be between 0.0 and 10.0")
    @DecimalMax(value = "10.0", inclusive = true, message = "Minimum CGPA must be between 0.0 and 10.0")
    private Double minimumCGPA;

    @NotNull(message = "Package amount cannot be null")
    @Positive(message = "Package amount must be positive")
    @JsonProperty("package")
    @JsonAlias({"packageAmount", "packageSalary"})
    private Double packageAmount;

    @NotBlank(message = "Location cannot be blank")
    @Size(max = 150, message = "Location must not exceed 150 characters")
    private String location;

    @NotNull(message = "Drive date cannot be null")
    private LocalDate driveDate;

    @NotNull(message = "Application deadline cannot be null")
    private LocalDate applicationDeadline;

    private DriveStatus status;

    public PlacementDriveRequestDTO() {
    }

    public PlacementDriveRequestDTO(Long companyId, String jobRole, String jobDescription,
                                    String eligibilityCriteria, Double minimumCGPA, Double packageAmount,
                                    String location, LocalDate driveDate, LocalDate applicationDeadline,
                                    DriveStatus status) {
        this.companyId = companyId;
        this.jobRole = jobRole;
        this.jobDescription = jobDescription;
        this.eligibilityCriteria = eligibilityCriteria;
        this.minimumCGPA = minimumCGPA;
        this.packageAmount = packageAmount;
        this.location = location;
        this.driveDate = driveDate;
        this.applicationDeadline = applicationDeadline;
        this.status = status;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
}
