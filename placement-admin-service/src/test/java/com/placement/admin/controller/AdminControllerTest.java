package com.placement.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.placement.admin.dto.admin.AdminLoginRequestDTO;
import com.placement.admin.dto.admin.AdminRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/admins - Should create admin successfully")
    void testCreateAdminSuccess() throws Exception {
        AdminRequestDTO request = new AdminRequestDTO("New Admin", "newadmin@example.com", "secure123", "+91 9988776655");

        mockMvc.perform(post("/api/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("Admin created successfully")))
                .andExpect(jsonPath("$.data.email", is("newadmin@example.com")))
                .andExpect(jsonPath("$.data.name", is("New Admin")))
                .andExpect(jsonPath("$.data.password").doesNotExist());
    }

    @Test
    @DisplayName("POST /api/admins - Should return 409 when email already exists")
    void testCreateAdminDuplicateEmail() throws Exception {
        AdminRequestDTO request = new AdminRequestDTO("Duplicate", "admin@placement.edu", "pass123", "+91 9988776655");

        mockMvc.perform(post("/api/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status", is(409)))
                .andExpect(jsonPath("$.message", containsString("already exists")));
    }

    @Test
    @DisplayName("GET /api/admins - Should return list of admins")
    void testGetAllAdmins() throws Exception {
        mockMvc.perform(get("/api/admins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Admins retrieved successfully")))
                .andExpect(jsonPath("$.data", hasSize(greaterThanOrEqualTo(3))))
                .andExpect(jsonPath("$.data[0].password").doesNotExist());
    }

    @Test
    @DisplayName("GET /api/admins/{id} - Should return admin by ID")
    void testGetAdminById() throws Exception {
        mockMvc.perform(get("/api/admins/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Admin retrieved successfully")))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.email", is("admin@placement.edu")));
    }

    @Test
    @DisplayName("POST /api/admins/login - Should login successfully with valid credentials")
    void testAdminLoginSuccess() throws Exception {
        AdminLoginRequestDTO loginRequest = new AdminLoginRequestDTO("admin@placement.edu", "admin123");

        mockMvc.perform(post("/api/admins/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.message", is("Login successful")))
                .andExpect(jsonPath("$.data.email", is("admin@placement.edu")));
    }

    @Test
    @DisplayName("POST /api/admins/login - Should fail with invalid credentials")
    void testAdminLoginFailure() throws Exception {
        AdminLoginRequestDTO loginRequest = new AdminLoginRequestDTO("admin@placement.edu", "wrongPassword");

        mockMvc.perform(post("/api/admins/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid email or password")));
    }
}
