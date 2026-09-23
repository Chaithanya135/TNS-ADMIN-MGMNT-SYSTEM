package com.placement.admin.controller;

import com.placement.admin.dto.admin.AdminLoginRequestDTO;
import com.placement.admin.dto.admin.AdminLoginResponseDTO;
import com.placement.admin.dto.admin.AdminRequestDTO;
import com.placement.admin.dto.admin.AdminResponseDTO;
import com.placement.admin.dto.common.ApiResponse;
import com.placement.admin.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AdminResponseDTO>> createAdmin(@Valid @RequestBody AdminRequestDTO requestDTO) {
        AdminResponseDTO createdAdmin = adminService.createAdmin(requestDTO);
        return new ResponseEntity<>(
                ApiResponse.of("Admin created successfully", createdAdmin),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AdminResponseDTO>>> getAllAdmins() {
        List<AdminResponseDTO> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(ApiResponse.of("Admins retrieved successfully", admins));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AdminResponseDTO>> getAdminById(@PathVariable Long id) {
        AdminResponseDTO admin = adminService.getAdminById(id);
        return ResponseEntity.ok(ApiResponse.of("Admin retrieved successfully", admin));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AdminResponseDTO>> updateAdmin(
            @PathVariable Long id,
            @Valid @RequestBody AdminRequestDTO requestDTO) {
        AdminResponseDTO updatedAdmin = adminService.updateAdmin(id, requestDTO);
        return ResponseEntity.ok(ApiResponse.of("Admin updated successfully", updatedAdmin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AdminLoginResponseDTO>> login(@Valid @RequestBody AdminLoginRequestDTO loginRequestDTO) {
        AdminLoginResponseDTO loginResponse = adminService.login(loginRequestDTO);
        return ResponseEntity.ok(ApiResponse.of("Login successful", loginResponse));
    }
}
