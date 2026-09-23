package com.placement.admin.repository;

import com.placement.admin.entity.Application;
import com.placement.admin.entity.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByStudentId(Long studentId);

    List<Application> findByPlacementDriveId(Long driveId);

    List<Application> findByStatus(ApplicationStatus status);

    boolean existsByStudentIdAndPlacementDriveId(Long studentId, Long driveId);

    Optional<Application> findByStudentIdAndPlacementDriveId(Long studentId, Long driveId);

    long countByStatus(ApplicationStatus status);
}
