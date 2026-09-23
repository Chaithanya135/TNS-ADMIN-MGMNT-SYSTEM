package com.placement.admin.repository;

import com.placement.admin.entity.Student;
import com.placement.admin.entity.enums.PlacementStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    Optional<Student> findByRollNumber(String rollNumber);

    boolean existsByEmail(String email);

    boolean existsByRollNumber(String rollNumber);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByRollNumberAndIdNot(String rollNumber, Long id);

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByDepartmentIgnoreCase(String department);

    List<Student> findByPlacementStatus(PlacementStatus placementStatus);

    long countByPlacementStatus(PlacementStatus placementStatus);
}
