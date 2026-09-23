package com.placement.admin.service.impl;

import com.placement.admin.dto.drive.PlacementDriveRequestDTO;
import com.placement.admin.dto.drive.PlacementDriveResponseDTO;
import com.placement.admin.entity.Company;
import com.placement.admin.entity.PlacementDrive;
import com.placement.admin.entity.enums.DriveStatus;
import com.placement.admin.exception.InvalidOperationException;
import com.placement.admin.exception.ResourceNotFoundException;
import com.placement.admin.repository.CompanyRepository;
import com.placement.admin.repository.PlacementDriveRepository;
import com.placement.admin.service.PlacementDriveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlacementDriveServiceImpl implements PlacementDriveService {

    private final PlacementDriveRepository placementDriveRepository;
    private final CompanyRepository companyRepository;

    public PlacementDriveServiceImpl(PlacementDriveRepository placementDriveRepository,
                                     CompanyRepository companyRepository) {
        this.placementDriveRepository = placementDriveRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public PlacementDriveResponseDTO createDrive(PlacementDriveRequestDTO requestDTO) {
        Company company = companyRepository.findById(requestDTO.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company with ID " + requestDTO.getCompanyId() + " not found"));

        validateDriveDates(requestDTO.getDriveDate(), requestDTO.getApplicationDeadline());

        PlacementDrive drive = new PlacementDrive();
        drive.setCompany(company);
        drive.setJobRole(requestDTO.getJobRole());
        drive.setJobDescription(requestDTO.getJobDescription());
        drive.setEligibilityCriteria(requestDTO.getEligibilityCriteria());
        drive.setMinimumCGPA(requestDTO.getMinimumCGPA());
        drive.setPackageAmount(requestDTO.getPackageAmount());
        drive.setLocation(requestDTO.getLocation());
        drive.setDriveDate(requestDTO.getDriveDate());
        drive.setApplicationDeadline(requestDTO.getApplicationDeadline());
        drive.setStatus(requestDTO.getStatus() != null ? requestDTO.getStatus() : DriveStatus.UPCOMING);

        PlacementDrive savedDrive = placementDriveRepository.save(drive);
        return mapToResponseDTO(savedDrive);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlacementDriveResponseDTO> getAllDrives() {
        return placementDriveRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PlacementDriveResponseDTO getDriveById(Long id) {
        PlacementDrive drive = placementDriveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Placement Drive with ID " + id + " not found"));
        return mapToResponseDTO(drive);
    }

    @Override
    public PlacementDriveResponseDTO updateDrive(Long id, PlacementDriveRequestDTO requestDTO) {
        PlacementDrive drive = placementDriveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Placement Drive with ID " + id + " not found"));

        if (!drive.getCompany().getId().equals(requestDTO.getCompanyId())) {
            Company newCompany = companyRepository.findById(requestDTO.getCompanyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Company with ID " + requestDTO.getCompanyId() + " not found"));
            drive.setCompany(newCompany);
        }

        validateDriveDates(requestDTO.getDriveDate(), requestDTO.getApplicationDeadline());

        drive.setJobRole(requestDTO.getJobRole());
        drive.setJobDescription(requestDTO.getJobDescription());
        drive.setEligibilityCriteria(requestDTO.getEligibilityCriteria());
        drive.setMinimumCGPA(requestDTO.getMinimumCGPA());
        drive.setPackageAmount(requestDTO.getPackageAmount());
        drive.setLocation(requestDTO.getLocation());
        drive.setDriveDate(requestDTO.getDriveDate());
        drive.setApplicationDeadline(requestDTO.getApplicationDeadline());
        if (requestDTO.getStatus() != null) {
            drive.setStatus(requestDTO.getStatus());
        }

        PlacementDrive updatedDrive = placementDriveRepository.save(drive);
        return mapToResponseDTO(updatedDrive);
    }

    @Override
    public void deleteDrive(Long id) {
        if (!placementDriveRepository.existsById(id)) {
            throw new ResourceNotFoundException("Placement Drive with ID " + id + " not found");
        }
        placementDriveRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlacementDriveResponseDTO> getDrivesByStatus(DriveStatus status) {
        return placementDriveRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlacementDriveResponseDTO> getDrivesByCompanyId(Long companyId) {
        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Company with ID " + companyId + " not found");
        }
        return placementDriveRepository.findByCompanyId(companyId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private void validateDriveDates(java.time.LocalDate driveDate, java.time.LocalDate applicationDeadline) {
        if (applicationDeadline != null && driveDate != null && applicationDeadline.isAfter(driveDate)) {
            throw new InvalidOperationException("Application deadline cannot be after the drive date");
        }
    }

    private PlacementDriveResponseDTO mapToResponseDTO(PlacementDrive drive) {
        return new PlacementDriveResponseDTO(
                drive.getId(),
                drive.getCompany() != null ? drive.getCompany().getId() : null,
                drive.getCompany() != null ? drive.getCompany().getCompanyName() : null,
                drive.getJobRole(),
                drive.getJobDescription(),
                drive.getEligibilityCriteria(),
                drive.getMinimumCGPA(),
                drive.getPackageAmount(),
                drive.getLocation(),
                drive.getDriveDate(),
                drive.getApplicationDeadline(),
                drive.getStatus(),
                drive.getCreatedAt(),
                drive.getUpdatedAt()
        );
    }
}
