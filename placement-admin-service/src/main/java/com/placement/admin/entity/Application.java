package com.placement.admin.entity;

import com.placement.admin.entity.enums.ApplicationStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"student_id", "drive_id"}, name = "uk_student_drive_application")
})
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drive_id", nullable = false)
    private PlacementDrive placementDrive;

    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    @Column(length = 500)
    private String remarks;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Application() {
    }

    public Application(Long id, Student student, PlacementDrive placementDrive, LocalDate applicationDate,
                       ApplicationStatus status, String remarks, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.student = student;
        this.placementDrive = placementDrive;
        this.applicationDate = applicationDate;
        this.status = status != null ? status : ApplicationStatus.APPLIED;
        this.remarks = remarks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.applicationDate == null) {
            this.applicationDate = LocalDate.now();
        }
        if (this.status == null) {
            this.status = ApplicationStatus.APPLIED;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public PlacementDrive getPlacementDrive() {
        return placementDrive;
    }

    public void setPlacementDrive(PlacementDrive placementDrive) {
        this.placementDrive = placementDrive;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
