package com.placement.admin.service.impl;

import com.placement.admin.dto.application.ApplicationRequestDTO;
import com.placement.admin.dto.application.ApplicationResponseDTO;
import com.placement.admin.dto.application.ApplicationStatusUpdateDTO;
import com.placement.admin.entity.Application;
import com.placement.admin.entity.PlacementDrive;
import com.placement.admin.entity.Student;
import com.placement.admin.entity.enums.ApplicationStatus;
import com.placement.admin.entity.enums.PlacementStatus;
import com.placement.admin.exception.DuplicateResourceException;
import com.placement.admin.exception.ResourceNotFoundException;
import com.placement.admin.repository.ApplicationRepository;
import com.placement.admin.repository.PlacementDriveRepository;
import com.placement.admin.repository.StudentRepository;
import com.placement.admin.service.ApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final PlacementDriveRepository placementDriveRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository,
                                  StudentRepository studentRepository,
                                  PlacementDriveRepository placementDriveRepository) {
        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.placementDriveRepository = placementDriveRepository;
    }

    @Override
    public ApplicationResponseDTO createApplication(ApplicationRequestDTO requestDTO) {
        Student student = studentRepository.findById(requestDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + requestDTO.getStudentId() + " not found"));

        PlacementDrive drive = placementDriveRepository.findById(requestDTO.getDriveId())
                .orElseThrow(() -> new ResourceNotFoundException("Placement Drive with ID " + requestDTO.getDriveId() + " not found"));

        if (applicationRepository.existsByStudentIdAndPlacementDriveId(requestDTO.getStudentId(), requestDTO.getDriveId())) {
            throw new DuplicateResourceException("Student (ID " + requestDTO.getStudentId() +
                    ") has already applied for Placement Drive (ID " + requestDTO.getDriveId() + ")");
        }

        Application application = new Application();
        application.setStudent(student);
        application.setPlacementDrive(drive);
        application.setApplicationDate(LocalDate.now());
        application.setStatus(ApplicationStatus.APPLIED);
        application.setRemarks(requestDTO.getRemarks());

        Application savedApplication = applicationRepository.save(application);
        return mapToResponseDTO(savedApplication);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationResponseDTO> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ApplicationResponseDTO getApplicationById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application with ID " + id + " not found"));
        return mapToResponseDTO(application);
    }

    @Override
    public ApplicationResponseDTO updateApplicationStatus(Long id, ApplicationStatusUpdateDTO statusUpdateDTO) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application with ID " + id + " not found"));

        application.setStatus(statusUpdateDTO.getStatus());
        if (statusUpdateDTO.getRemarks() != null) {
            application.setRemarks(statusUpdateDTO.getRemarks());
        }

        // If the student is SELECTED, automatically update the student's placement status to PLACED
        if (statusUpdateDTO.getStatus() == ApplicationStatus.SELECTED) {
            Student student = application.getStudent();
            student.setPlacementStatus(PlacementStatus.PLACED);
            studentRepository.save(student);
        }

        Application updatedApplication = applicationRepository.save(application);
        return mapToResponseDTO(updatedApplication);
    }

    @Override
    public void deleteApplication(Long id) {
        if (!applicationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Application with ID " + id + " not found");
        }
        applicationRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationResponseDTO> getApplicationsByStudentId(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student with ID " + studentId + " not found");
        }
        return applicationRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationResponseDTO> getApplicationsByDriveId(Long driveId) {
        if (!placementDriveRepository.existsById(driveId)) {
            throw new ResourceNotFoundException("Placement Drive with ID " + driveId + " not found");
        }
        return applicationRepository.findByPlacementDriveId(driveId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationResponseDTO> getApplicationsByStatus(ApplicationStatus status) {
        return applicationRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private ApplicationResponseDTO mapToResponseDTO(Application application) {
        return new ApplicationResponseDTO(
                application.getId(),
                application.getStudent() != null ? application.getStudent().getId() : null,
                application.getStudent() != null ? application.getStudent().getName() : null,
                application.getStudent() != null ? application.getStudent().getRollNumber() : null,
                application.getStudent() != null ? application.getStudent().getEmail() : null,
                application.getPlacementDrive() != null ? application.getPlacementDrive().getId() : null,
                (application.getPlacementDrive() != null && application.getPlacementDrive().getCompany() != null)
                        ? application.getPlacementDrive().getCompany().getCompanyName() : null,
                application.getPlacementDrive() != null ? application.getPlacementDrive().getJobRole() : null,
                application.getApplicationDate(),
                application.getStatus(),
                application.getRemarks(),
                application.getCreatedAt(),
                application.getUpdatedAt()
        );
    }
}
