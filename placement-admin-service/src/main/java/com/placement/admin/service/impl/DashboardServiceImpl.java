package com.placement.admin.service.impl;

import com.placement.admin.dto.dashboard.DashboardResponseDTO;
import com.placement.admin.entity.enums.ApplicationStatus;
import com.placement.admin.entity.enums.DriveStatus;
import com.placement.admin.entity.enums.PlacementStatus;
import com.placement.admin.repository.ApplicationRepository;
import com.placement.admin.repository.CompanyRepository;
import com.placement.admin.repository.PlacementDriveRepository;
import com.placement.admin.repository.StudentRepository;
import com.placement.admin.service.DashboardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DashboardServiceImpl implements DashboardService {

    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final PlacementDriveRepository placementDriveRepository;
    private final ApplicationRepository applicationRepository;

    public DashboardServiceImpl(StudentRepository studentRepository,
                                CompanyRepository companyRepository,
                                PlacementDriveRepository placementDriveRepository,
                                ApplicationRepository applicationRepository) {
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.placementDriveRepository = placementDriveRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public DashboardResponseDTO getDashboardStatistics() {
        long totalStudents = studentRepository.count();
        long totalCompanies = companyRepository.count();
        long totalPlacementDrives = placementDriveRepository.count();
        long totalApplications = applicationRepository.count();

        long placedStudents = studentRepository.countByPlacementStatus(PlacementStatus.PLACED);
        long unplacedStudents = studentRepository.countByPlacementStatus(PlacementStatus.NOT_PLACED);

        long selectedApplications = applicationRepository.countByStatus(ApplicationStatus.SELECTED);
        long rejectedApplications = applicationRepository.countByStatus(ApplicationStatus.REJECTED);
        long shortlistedApplications = applicationRepository.countByStatus(ApplicationStatus.SHORTLISTED);

        long upcomingDrives = placementDriveRepository.countByStatus(DriveStatus.UPCOMING);
        long openDrives = placementDriveRepository.countByStatus(DriveStatus.OPEN);

        return new DashboardResponseDTO(
                totalStudents,
                totalCompanies,
                totalPlacementDrives,
                totalApplications,
                placedStudents,
                unplacedStudents,
                selectedApplications,
                rejectedApplications,
                shortlistedApplications,
                upcomingDrives,
                openDrives
        );
    }
}
