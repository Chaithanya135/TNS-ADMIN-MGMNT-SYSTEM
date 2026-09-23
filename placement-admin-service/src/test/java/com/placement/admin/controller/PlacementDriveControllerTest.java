package com.placement.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.placement.admin.dto.drive.PlacementDriveRequestDTO;
import com.placement.admin.entity.enums.DriveStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PlacementDriveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/drives - Should create drive successfully")
    void testCreateDriveSuccess() throws Exception {
        PlacementDriveRequestDTO request = new PlacementDriveRequestDTO(
                1L, "Senior Data Analyst", "Analyze enterprise pipelines and metrics",
                "B.Tech (CSE/IT)", 7.5, 12.0, "Bengaluru",
                LocalDate.now().plusDays(25), LocalDate.now().plusDays(10), DriveStatus.OPEN
        );

        mockMvc.perform(post("/api/drives")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("Placement drive created successfully")))
                .andExpect(jsonPath("$.data.jobRole", is("Senior Data Analyst")))
                .andExpect(jsonPath("$.data.package", is(12.0)));
    }

    @Test
    @DisplayName("POST /api/drives - Should fail when company does not exist")
    void testCreateDriveInvalidCompany() throws Exception {
        PlacementDriveRequestDTO request = new PlacementDriveRequestDTO(
                9999L, "Software Engineer", "Desc", "B.Tech", 7.0, 10.0, "Pune",
                LocalDate.now().plusDays(20), LocalDate.now().plusDays(10), DriveStatus.OPEN
        );

        mockMvc.perform(post("/api/drives")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", containsString("Company with ID 9999 not found")));
    }

    @Test
    @DisplayName("POST /api/drives - Should fail when deadline is after drive date")
    void testCreateDriveInvalidDeadline() throws Exception {
        PlacementDriveRequestDTO request = new PlacementDriveRequestDTO(
                1L, "Software Engineer", "Desc", "B.Tech", 7.0, 10.0, "Pune",
                LocalDate.now().plusDays(10), LocalDate.now().plusDays(20), DriveStatus.OPEN
        );

        mockMvc.perform(post("/api/drives")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.message", containsString("Application deadline cannot be after the drive date")));
    }

    @Test
    @DisplayName("GET /api/drives - Should return list of placement drives")
    void testGetAllDrives() throws Exception {
        mockMvc.perform(get("/api/drives"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Placement drives retrieved successfully")))
                .andExpect(jsonPath("$.data", hasSize(greaterThanOrEqualTo(5))));
    }

    @Test
    @DisplayName("GET /api/drives/{id} - Should return drive by ID")
    void testGetDriveById() throws Exception {
        mockMvc.perform(get("/api/drives/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.jobRole", notNullValue()));
    }

    @Test
    @DisplayName("GET /api/drives/status/OPEN - Should return open drives")
    void testGetDrivesByStatus() throws Exception {
        mockMvc.perform(get("/api/drives/status/OPEN"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$.data[0].status", is("OPEN")));
    }
}
