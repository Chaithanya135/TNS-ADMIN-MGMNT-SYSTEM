package com.placement.admin.controller;

import com.placement.admin.dto.common.ApiResponse;
import com.placement.admin.dto.drive.PlacementDriveRequestDTO;
import com.placement.admin.dto.drive.PlacementDriveResponseDTO;
import com.placement.admin.entity.enums.DriveStatus;
import com.placement.admin.service.PlacementDriveService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drives")
public class PlacementDriveController {

    private final PlacementDriveService placementDriveService;

    public PlacementDriveController(PlacementDriveService placementDriveService) {
        this.placementDriveService = placementDriveService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PlacementDriveResponseDTO>> createDrive(
            @Valid @RequestBody PlacementDriveRequestDTO requestDTO) {
        PlacementDriveResponseDTO createdDrive = placementDriveService.createDrive(requestDTO);
        return new ResponseEntity<>(
                ApiResponse.of("Placement drive created successfully", createdDrive),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PlacementDriveResponseDTO>>> getAllDrives() {
        List<PlacementDriveResponseDTO> drives = placementDriveService.getAllDrives();
        return ResponseEntity.ok(ApiResponse.of("Placement drives retrieved successfully", drives));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PlacementDriveResponseDTO>> getDriveById(@PathVariable Long id) {
        PlacementDriveResponseDTO drive = placementDriveService.getDriveById(id);
        return ResponseEntity.ok(ApiResponse.of("Placement drive retrieved successfully", drive));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PlacementDriveResponseDTO>> updateDrive(
            @PathVariable Long id,
            @Valid @RequestBody PlacementDriveRequestDTO requestDTO) {
        PlacementDriveResponseDTO updatedDrive = placementDriveService.updateDrive(id, requestDTO);
        return ResponseEntity.ok(ApiResponse.of("Placement drive updated successfully", updatedDrive));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrive(@PathVariable Long id) {
        placementDriveService.deleteDrive(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<PlacementDriveResponseDTO>>> getDrivesByStatus(
            @PathVariable DriveStatus status) {
        List<PlacementDriveResponseDTO> drives = placementDriveService.getDrivesByStatus(status);
        return ResponseEntity.ok(ApiResponse.of("Placement drives retrieved for status: " + status, drives));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<ApiResponse<List<PlacementDriveResponseDTO>>> getDrivesByCompanyId(
            @PathVariable Long companyId) {
        List<PlacementDriveResponseDTO> drives = placementDriveService.getDrivesByCompanyId(companyId);
        return ResponseEntity.ok(ApiResponse.of("Placement drives retrieved for company ID: " + companyId, drives));
    }
}
