package com.placement.admin.entity;

import com.placement.admin.entity.enums.DriveStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "placement_drives")
public class PlacementDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "job_role", nullable = false, length = 100)
    private String jobRole;

    @Column(name = "job_description", length = 2000)
    private String jobDescription;

    @Column(name = "eligibility_criteria", length = 500)
    private String eligibilityCriteria;

    @Column(name = "minimum_cgpa", nullable = false)
    private Double minimumCGPA;

    @Column(name = "package_amount", nullable = false)
    private Double packageAmount;

    @Column(nullable = false, length = 150)
    private String location;

    @Column(name = "drive_date", nullable = false)
    private LocalDate driveDate;

    @Column(name = "application_deadline", nullable = false)
    private LocalDate applicationDeadline;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DriveStatus status = DriveStatus.UPCOMING;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "placementDrive", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications = new ArrayList<>();

    public PlacementDrive() {
    }

    public PlacementDrive(Long id, Company company, String jobRole, String jobDescription,
                          String eligibilityCriteria, Double minimumCGPA, Double packageAmount,
                          String location, LocalDate driveDate, LocalDate applicationDeadline,
                          DriveStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.company = company;
        this.jobRole = jobRole;
        this.jobDescription = jobDescription;
        this.eligibilityCriteria = eligibilityCriteria;
        this.minimumCGPA = minimumCGPA;
        this.packageAmount = packageAmount;
        this.location = location;
        this.driveDate = driveDate;
        this.applicationDeadline = applicationDeadline;
        this.status = status != null ? status : DriveStatus.UPCOMING;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = DriveStatus.UPCOMING;
        }
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}
