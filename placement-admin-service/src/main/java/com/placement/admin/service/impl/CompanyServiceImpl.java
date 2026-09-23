package com.placement.admin.service.impl;

import com.placement.admin.dto.company.CompanyRequestDTO;
import com.placement.admin.dto.company.CompanyResponseDTO;
import com.placement.admin.entity.Company;
import com.placement.admin.entity.enums.CompanyStatus;
import com.placement.admin.exception.DuplicateResourceException;
import com.placement.admin.exception.ResourceNotFoundException;
import com.placement.admin.repository.CompanyRepository;
import com.placement.admin.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyResponseDTO createCompany(CompanyRequestDTO requestDTO) {
        if (companyRepository.existsByCompanyName(requestDTO.getCompanyName())) {
            throw new DuplicateResourceException("Company with name '" + requestDTO.getCompanyName() + "' already exists");
        }
        if (requestDTO.getEmail() != null && !requestDTO.getEmail().isBlank()) {
            if (companyRepository.existsByEmail(requestDTO.getEmail())) {
                throw new DuplicateResourceException("Company with email " + requestDTO.getEmail() + " already exists");
            }
        }

        Company company = new Company();
        company.setCompanyName(requestDTO.getCompanyName());
        company.setEmail(requestDTO.getEmail());
        company.setPhone(requestDTO.getPhone());
        company.setWebsite(requestDTO.getWebsite());
        company.setIndustry(requestDTO.getIndustry());
        company.setLocation(requestDTO.getLocation());
        company.setDescription(requestDTO.getDescription());
        company.setStatus(requestDTO.getStatus() != null ? requestDTO.getStatus() : CompanyStatus.ACTIVE);

        Company savedCompany = companyRepository.save(company);
        return mapToResponseDTO(savedCompany);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponseDTO> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponseDTO getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company with ID " + id + " not found"));
        return mapToResponseDTO(company);
    }

    @Override
    public CompanyResponseDTO updateCompany(Long id, CompanyRequestDTO requestDTO) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company with ID " + id + " not found"));

        if (companyRepository.existsByCompanyNameAndIdNot(requestDTO.getCompanyName(), id)) {
            throw new DuplicateResourceException("Company with name '" + requestDTO.getCompanyName() + "' already exists");
        }
        if (requestDTO.getEmail() != null && !requestDTO.getEmail().isBlank()) {
            if (companyRepository.existsByEmailAndIdNot(requestDTO.getEmail(), id)) {
                throw new DuplicateResourceException("Company with email " + requestDTO.getEmail() + " already exists");
            }
        }

        company.setCompanyName(requestDTO.getCompanyName());
        company.setEmail(requestDTO.getEmail());
        company.setPhone(requestDTO.getPhone());
        company.setWebsite(requestDTO.getWebsite());
        company.setIndustry(requestDTO.getIndustry());
        company.setLocation(requestDTO.getLocation());
        company.setDescription(requestDTO.getDescription());
        if (requestDTO.getStatus() != null) {
            company.setStatus(requestDTO.getStatus());
        }

        Company updatedCompany = companyRepository.save(company);
        return mapToResponseDTO(updatedCompany);
    }

    @Override
    public void deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Company with ID " + id + " not found");
        }
        companyRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponseDTO> getCompaniesByStatus(CompanyStatus status) {
        return companyRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponseDTO> searchCompaniesByName(String name) {
        return companyRepository.findByCompanyNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private CompanyResponseDTO mapToResponseDTO(Company company) {
        return new CompanyResponseDTO(
                company.getId(),
                company.getCompanyName(),
                company.getEmail(),
                company.getPhone(),
                company.getWebsite(),
                company.getIndustry(),
                company.getLocation(),
                company.getDescription(),
                company.getStatus(),
                company.getCreatedAt()
        );
    }
}
