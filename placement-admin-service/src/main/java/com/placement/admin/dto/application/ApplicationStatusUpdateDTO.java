package com.placement.admin.dto.application;

import com.placement.admin.entity.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ApplicationStatusUpdateDTO {

    @NotNull(message = "Application status cannot be null")
    private ApplicationStatus status;

    @Size(max = 500, message = "Remarks must not exceed 500 characters")
    private String remarks;

    public ApplicationStatusUpdateDTO() {
    }

    public ApplicationStatusUpdateDTO(ApplicationStatus status, String remarks) {
        this.status = status;
        this.remarks = remarks;
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
}
