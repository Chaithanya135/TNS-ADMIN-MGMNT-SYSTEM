package com.placement.admin.dto.application;

import com.placement.admin.entity.enums.ApplicationStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ApplicationResponseDTO {

    private Long id;
    private Long studentId;
    private String studentName;
    private String studentRollNumber;
    private String studentEmail;
    private Long driveId;
    private String companyName;
    private String jobRole;
    private LocalDate applicationDate;
    private ApplicationStatus status;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ApplicationResponseDTO() {
    }

    public ApplicationResponseDTO(Long id, Long studentId, String studentName, String studentRollNumber,
                                  String studentEmail, Long driveId, String companyName, String jobRole,
                                  LocalDate applicationDate, ApplicationStatus status, String remarks,
                                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentRollNumber = studentRollNumber;
        this.studentEmail = studentEmail;
        this.driveId = driveId;
        this.companyName = companyName;
        this.jobRole = jobRole;
        this.applicationDate = applicationDate;
        this.status = status;
        this.remarks = remarks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentRollNumber() {
        return studentRollNumber;
    }

    public void setStudentRollNumber(String studentRollNumber) {
        this.studentRollNumber = studentRollNumber;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Long getDriveId() {
        return driveId;
    }

    public void setDriveId(Long driveId) {
        this.driveId = driveId;
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
