package com.placement.admin.service;

import com.placement.admin.dto.admin.AdminLoginRequestDTO;
import com.placement.admin.dto.admin.AdminLoginResponseDTO;
import com.placement.admin.dto.admin.AdminRequestDTO;
import com.placement.admin.dto.admin.AdminResponseDTO;

import java.util.List;

public interface AdminService {

    AdminResponseDTO createAdmin(AdminRequestDTO requestDTO);

    List<AdminResponseDTO> getAllAdmins();

    AdminResponseDTO getAdminById(Long id);

    AdminResponseDTO updateAdmin(Long id, AdminRequestDTO requestDTO);

    void deleteAdmin(Long id);

    AdminLoginResponseDTO login(AdminLoginRequestDTO loginRequestDTO);
}
