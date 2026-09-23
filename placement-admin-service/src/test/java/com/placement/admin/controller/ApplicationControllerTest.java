package com.placement.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.placement.admin.dto.application.ApplicationRequestDTO;
import com.placement.admin.dto.application.ApplicationStatusUpdateDTO;
import com.placement.admin.entity.enums.ApplicationStatus;
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
class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/applications - Should create application successfully")
    void testCreateApplicationSuccess() throws Exception {
        // Student 10 applied for Drive 2 (not applied yet)
        ApplicationRequestDTO request = new ApplicationRequestDTO(10L, 2L, "Applying for Cloud DevOps Trainee");

        mockMvc.perform(post("/api/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("Application created successfully")))
                .andExpect(jsonPath("$.data.studentId", is(10)))
                .andExpect(jsonPath("$.data.driveId", is(2)))
                .andExpect(jsonPath("$.data.status", is("APPLIED")));
    }

    @Test
    @DisplayName("POST /api/applications - Should prevent duplicate application for same student and drive")
    void testDuplicateApplication() throws Exception {
        // Student 1 has already applied for Drive 1 in seeded data
        ApplicationRequestDTO request = new ApplicationRequestDTO(1L, 1L, "Applying again");

        mockMvc.perform(post("/api/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status", is(409)))
                .andExpect(jsonPath("$.message", containsString("already applied")));
    }

    @Test
    @DisplayName("PUT /api/applications/{id} - Should update application status to SELECTED and update student status to PLACED")
    void testUpdateApplicationStatusToSelected() throws Exception {
        // Student 1 (Application 1 is SHORTLISTED currently)
        ApplicationStatusUpdateDTO updateDTO = new ApplicationStatusUpdateDTO(ApplicationStatus.SELECTED, "Final offer issued");

        mockMvc.perform(put("/api/applications/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Application status updated successfully")))
                .andExpect(jsonPath("$.data.status", is("SELECTED")));

        // Verify student 1 status changed to PLACED
        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.placementStatus", is("PLACED")));
    }

    @Test
    @DisplayName("GET /api/applications - Should return all applications")
    void testGetAllApplications() throws Exception {
        mockMvc.perform(get("/api/applications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Applications retrieved successfully")))
                .andExpect(jsonPath("$.data", hasSize(greaterThanOrEqualTo(15))));
    }

    @Test
    @DisplayName("GET /api/applications/student/{studentId} - Should return applications for student")
    void testGetApplicationsByStudentId() throws Exception {
        mockMvc.perform(get("/api/applications/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    @DisplayName("GET /api/applications/drive/{driveId} - Should return applications for drive")
    void testGetApplicationsByDriveId() throws Exception {
        mockMvc.perform(get("/api/applications/drive/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(greaterThanOrEqualTo(1))));
    }
}
