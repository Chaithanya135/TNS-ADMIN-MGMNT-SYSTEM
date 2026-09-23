package com.placement.admin.dto.application;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ApplicationRequestDTO {

    @NotNull(message = "Student ID cannot be null")
    private Long studentId;

    @NotNull(message = "Placement drive ID cannot be null")
    private Long driveId;

    @Size(max = 500, message = "Remarks must not exceed 500 characters")
    private String remarks;

    public ApplicationRequestDTO() {
    }

    public ApplicationRequestDTO(Long studentId, Long driveId, String remarks) {
        this.studentId = studentId;
        this.driveId = driveId;
        this.remarks = remarks;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getDriveId() {
        return driveId;
    }

    public void setDriveId(Long driveId) {
        this.driveId = driveId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
