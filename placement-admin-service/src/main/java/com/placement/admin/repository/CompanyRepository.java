package com.placement.admin.repository;

import com.placement.admin.entity.Company;
import com.placement.admin.entity.enums.CompanyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findByCompanyNameContainingIgnoreCase(String companyName);

    List<Company> findByStatus(CompanyStatus status);

    boolean existsByCompanyName(String companyName);

    boolean existsByCompanyNameAndIdNot(String companyName, Long id);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    Optional<Company> findByCompanyName(String companyName);
}
