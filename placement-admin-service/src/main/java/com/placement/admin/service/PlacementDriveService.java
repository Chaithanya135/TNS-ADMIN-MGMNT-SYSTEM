package com.placement.admin.service;

import com.placement.admin.dto.drive.PlacementDriveRequestDTO;
import com.placement.admin.dto.drive.PlacementDriveResponseDTO;
import com.placement.admin.entity.enums.DriveStatus;

import java.util.List;

public interface PlacementDriveService {

    PlacementDriveResponseDTO createDrive(PlacementDriveRequestDTO requestDTO);

    List<PlacementDriveResponseDTO> getAllDrives();

    PlacementDriveResponseDTO getDriveById(Long id);

    PlacementDriveResponseDTO updateDrive(Long id, PlacementDriveRequestDTO requestDTO);

    void deleteDrive(Long id);

    List<PlacementDriveResponseDTO> getDrivesByStatus(DriveStatus status);

    List<PlacementDriveResponseDTO> getDrivesByCompanyId(Long companyId);
}
