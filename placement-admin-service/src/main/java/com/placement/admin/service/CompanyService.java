package com.placement.admin.service;

import com.placement.admin.dto.company.CompanyRequestDTO;
import com.placement.admin.dto.company.CompanyResponseDTO;
import com.placement.admin.entity.enums.CompanyStatus;

import java.util.List;

public interface CompanyService {

    CompanyResponseDTO createCompany(CompanyRequestDTO requestDTO);

    List<CompanyResponseDTO> getAllCompanies();

    CompanyResponseDTO getCompanyById(Long id);

    CompanyResponseDTO updateCompany(Long id, CompanyRequestDTO requestDTO);

    void deleteCompany(Long id);

    List<CompanyResponseDTO> getCompaniesByStatus(CompanyStatus status);

    List<CompanyResponseDTO> searchCompaniesByName(String name);
}
