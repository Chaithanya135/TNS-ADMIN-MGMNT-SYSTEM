package com.placement.admin.service.impl;

import com.placement.admin.dto.admin.AdminLoginRequestDTO;
import com.placement.admin.dto.admin.AdminLoginResponseDTO;
import com.placement.admin.dto.admin.AdminRequestDTO;
import com.placement.admin.dto.admin.AdminResponseDTO;
import com.placement.admin.entity.Admin;
import com.placement.admin.entity.enums.AdminRole;
import com.placement.admin.exception.DuplicateResourceException;
import com.placement.admin.exception.InvalidOperationException;
import com.placement.admin.exception.ResourceNotFoundException;
import com.placement.admin.repository.AdminRepository;
import com.placement.admin.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public AdminResponseDTO createAdmin(AdminRequestDTO requestDTO) {
        if (adminRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateResourceException("Admin with email " + requestDTO.getEmail() + " already exists");
        }

        Admin admin = new Admin();
        admin.setName(requestDTO.getName());
        admin.setEmail(requestDTO.getEmail());
        admin.setPassword(requestDTO.getPassword());
        admin.setPhone(requestDTO.getPhone());
        admin.setRole(AdminRole.ADMIN);

        Admin savedAdmin = adminRepository.save(admin);
        return mapToResponseDTO(savedAdmin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdminResponseDTO> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AdminResponseDTO getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin with ID " + id + " not found"));
        return mapToResponseDTO(admin);
    }

    @Override
    public AdminResponseDTO updateAdmin(Long id, AdminRequestDTO requestDTO) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin with ID " + id + " not found"));

        if (adminRepository.existsByEmailAndIdNot(requestDTO.getEmail(), id)) {
            throw new DuplicateResourceException("Admin with email " + requestDTO.getEmail() + " already exists");
        }

        admin.setName(requestDTO.getName());
        admin.setEmail(requestDTO.getEmail());
        if (requestDTO.getPassword() != null && !requestDTO.getPassword().isBlank()) {
            admin.setPassword(requestDTO.getPassword());
        }
        admin.setPhone(requestDTO.getPhone());

        Admin updatedAdmin = adminRepository.save(admin);
        return mapToResponseDTO(updatedAdmin);
    }

    @Override
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new ResourceNotFoundException("Admin with ID " + id + " not found");
        }
        adminRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public AdminLoginResponseDTO login(AdminLoginRequestDTO loginRequestDTO) {
        Admin admin = adminRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new InvalidOperationException("Invalid email or password"));

        if (!admin.getPassword().equals(loginRequestDTO.getPassword())) {
            throw new InvalidOperationException("Invalid email or password");
        }

        return new AdminLoginResponseDTO(
                admin.getId(),
                admin.getName(),
                admin.getEmail(),
                admin.getRole(),
                "Login successful"
        );
    }

    private AdminResponseDTO mapToResponseDTO(Admin admin) {
        return new AdminResponseDTO(
                admin.getId(),
                admin.getName(),
                admin.getEmail(),
                admin.getPhone(),
                admin.getRole(),
                admin.getCreatedAt()
        );
    }
}
