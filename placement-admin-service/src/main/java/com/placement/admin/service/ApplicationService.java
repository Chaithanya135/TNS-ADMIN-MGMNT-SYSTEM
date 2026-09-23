package com.placement.admin.service;

import com.placement.admin.dto.application.ApplicationRequestDTO;
import com.placement.admin.dto.application.ApplicationResponseDTO;
import com.placement.admin.dto.application.ApplicationStatusUpdateDTO;
import com.placement.admin.entity.enums.ApplicationStatus;

import java.util.List;

public interface ApplicationService {

    ApplicationResponseDTO createApplication(ApplicationRequestDTO requestDTO);

    List<ApplicationResponseDTO> getAllApplications();

    ApplicationResponseDTO getApplicationById(Long id);

    ApplicationResponseDTO updateApplicationStatus(Long id, ApplicationStatusUpdateDTO statusUpdateDTO);

    void deleteApplication(Long id);

    List<ApplicationResponseDTO> getApplicationsByStudentId(Long studentId);

    List<ApplicationResponseDTO> getApplicationsByDriveId(Long driveId);

    List<ApplicationResponseDTO> getApplicationsByStatus(ApplicationStatus status);
}
