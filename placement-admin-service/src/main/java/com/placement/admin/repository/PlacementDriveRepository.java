package com.placement.admin.repository;

import com.placement.admin.entity.PlacementDrive;
import com.placement.admin.entity.enums.DriveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlacementDriveRepository extends JpaRepository<PlacementDrive, Long> {

    List<PlacementDrive> findByStatus(DriveStatus status);

    List<PlacementDrive> findByCompanyId(Long companyId);

    long countByStatus(DriveStatus status);
}
