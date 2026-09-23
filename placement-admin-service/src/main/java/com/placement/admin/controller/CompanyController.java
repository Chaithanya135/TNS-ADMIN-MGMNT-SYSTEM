package com.placement.admin.controller;

import com.placement.admin.dto.common.ApiResponse;
import com.placement.admin.dto.company.CompanyRequestDTO;
import com.placement.admin.dto.company.CompanyResponseDTO;
import com.placement.admin.entity.enums.CompanyStatus;
import com.placement.admin.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CompanyResponseDTO>> createCompany(@Valid @RequestBody CompanyRequestDTO requestDTO) {
        CompanyResponseDTO createdCompany = companyService.createCompany(requestDTO);
        return new ResponseEntity<>(
                ApiResponse.of("Company created successfully", createdCompany),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CompanyResponseDTO>>> getAllCompanies() {
        List<CompanyResponseDTO> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(ApiResponse.of("Companies retrieved successfully", companies));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyResponseDTO>> getCompanyById(@PathVariable Long id) {
        CompanyResponseDTO company = companyService.getCompanyById(id);
        return ResponseEntity.ok(ApiResponse.of("Company retrieved successfully", company));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyResponseDTO>> updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody CompanyRequestDTO requestDTO) {
        CompanyResponseDTO updatedCompany = companyService.updateCompany(id, requestDTO);
        return ResponseEntity.ok(ApiResponse.of("Company updated successfully", updatedCompany));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<CompanyResponseDTO>>> getCompaniesByStatus(
            @PathVariable CompanyStatus status) {
        List<CompanyResponseDTO> companies = companyService.getCompaniesByStatus(status);
        return ResponseEntity.ok(ApiResponse.of("Companies retrieved for status: " + status, companies));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<CompanyResponseDTO>>> searchCompaniesByName(
            @RequestParam(required = false, defaultValue = "") String name) {
        List<CompanyResponseDTO> companies = companyService.searchCompaniesByName(name);
        return ResponseEntity.ok(ApiResponse.of("Companies found successfully", companies));
    }
}
