package com.placement.admin.controller;

import com.placement.admin.dto.application.ApplicationRequestDTO;
import com.placement.admin.dto.application.ApplicationResponseDTO;
import com.placement.admin.dto.application.ApplicationStatusUpdateDTO;
import com.placement.admin.dto.common.ApiResponse;
import com.placement.admin.entity.enums.ApplicationStatus;
import com.placement.admin.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ApplicationResponseDTO>> createApplication(
            @Valid @RequestBody ApplicationRequestDTO requestDTO) {
        ApplicationResponseDTO createdApplication = applicationService.createApplication(requestDTO);
        return new ResponseEntity<>(
                ApiResponse.of("Application created successfully", createdApplication),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ApplicationResponseDTO>>> getAllApplications() {
        List<ApplicationResponseDTO> applications = applicationService.getAllApplications();
        return ResponseEntity.ok(ApiResponse.of("Applications retrieved successfully", applications));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ApplicationResponseDTO>> getApplicationById(@PathVariable Long id) {
        ApplicationResponseDTO application = applicationService.getApplicationById(id);
        return ResponseEntity.ok(ApiResponse.of("Application retrieved successfully", application));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ApplicationResponseDTO>> updateApplicationStatus(
            @PathVariable Long id,
            @Valid @RequestBody ApplicationStatusUpdateDTO statusUpdateDTO) {
        ApplicationResponseDTO updatedApplication = applicationService.updateApplicationStatus(id, statusUpdateDTO);
        return ResponseEntity.ok(ApiResponse.of("Application status updated successfully", updatedApplication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<ApplicationResponseDTO>>> getApplicationsByStudentId(
            @PathVariable Long studentId) {
        List<ApplicationResponseDTO> applications = applicationService.getApplicationsByStudentId(studentId);
        return ResponseEntity.ok(ApiResponse.of("Applications retrieved for student ID: " + studentId, applications));
    }

    @GetMapping("/drive/{driveId}")
    public ResponseEntity<ApiResponse<List<ApplicationResponseDTO>>> getApplicationsByDriveId(
            @PathVariable Long driveId) {
        List<ApplicationResponseDTO> applications = applicationService.getApplicationsByDriveId(driveId);
        return ResponseEntity.ok(ApiResponse.of("Applications retrieved for drive ID: " + driveId, applications));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<ApplicationResponseDTO>>> getApplicationsByStatus(
            @PathVariable ApplicationStatus status) {
        List<ApplicationResponseDTO> applications = applicationService.getApplicationsByStatus(status);
        return ResponseEntity.ok(ApiResponse.of("Applications retrieved for status: " + status, applications));
    }
}
