package com.placement.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.placement.admin.dto.student.StudentRequestDTO;
import com.placement.admin.entity.enums.PlacementStatus;
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
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/students - Should create student successfully")
    void testCreateStudentSuccess() throws Exception {
        StudentRequestDTO request = new StudentRequestDTO(
                "Suresh Raina", "suresh.raina@example.com", "+91 9888877777", "CSE999",
                "Computer Science", "B.Tech", 2026, 8.8, "Java, Spring", PlacementStatus.NOT_PLACED
        );

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("Student created successfully")))
                .andExpect(jsonPath("$.data.email", is("suresh.raina@example.com")))
                .andExpect(jsonPath("$.data.rollNumber", is("CSE999")))
                .andExpect(jsonPath("$.data.cgpa", is(8.8)));
    }

    @Test
    @DisplayName("POST /api/students - Should return 409 for duplicate email")
    void testCreateStudentDuplicateEmail() throws Exception {
        StudentRequestDTO request = new StudentRequestDTO(
                "Duplicate Email Student", "rahul.kumar@example.com", "+91 9888877777", "CSE888",
                "Computer Science", "B.Tech", 2026, 8.5, "Java", PlacementStatus.NOT_PLACED
        );

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message", containsString("already exists")));
    }

    @Test
    @DisplayName("POST /api/students - Should return 409 for duplicate roll number")
    void testCreateStudentDuplicateRollNumber() throws Exception {
        StudentRequestDTO request = new StudentRequestDTO(
                "Duplicate Roll Student", "unique.email@example.com", "+91 9888877777", "CSE001",
                "Computer Science", "B.Tech", 2026, 8.5, "Java", PlacementStatus.NOT_PLACED
        );

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message", containsString("already exists")));
    }

    @Test
    @DisplayName("POST /api/students - Should return 400 for invalid CGPA (> 10.0)")
    void testCreateStudentInvalidCGPA() throws Exception {
        StudentRequestDTO request = new StudentRequestDTO(
                "Invalid CGPA Student", "invalid.cgpa@example.com", "+91 9888877777", "CSE777",
                "Computer Science", "B.Tech", 2026, 11.5, "Java", PlacementStatus.NOT_PLACED
        );

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors.cgpa", is("CGPA must be between 0.0 and 10.0")));
    }

    @Test
    @DisplayName("GET /api/students - Should return all students")
    void testGetAllStudents() throws Exception {
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Students retrieved successfully")))
                .andExpect(jsonPath("$.data", hasSize(greaterThanOrEqualTo(10))));
    }

    @Test
    @DisplayName("GET /api/students/{id} - Should return 404 for nonexistent student")
    void testGetStudentByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/students/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", containsString("Student with ID 9999 not found")));
    }

    @Test
    @DisplayName("PUT /api/students/{id} - Should update student successfully")
    void testUpdateStudentSuccess() throws Exception {
        StudentRequestDTO updateRequest = new StudentRequestDTO(
                "Rahul K. Updated", "rahul.kumar@example.com", "+91 9123456780", "CSE001",
                "Computer Science", "B.Tech", 2026, 9.0, "Java, Spring Boot, AWS", PlacementStatus.NOT_PLACED
        );

        mockMvc.perform(put("/api/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Student updated successfully")))
                .andExpect(jsonPath("$.data.name", is("Rahul K. Updated")))
                .andExpect(jsonPath("$.data.cgpa", is(9.0)));
    }

    @Test
    @DisplayName("DELETE /api/students/{id} - Should return 204 No Content")
    void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/api/students/10"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /api/students/search?name=Rahul - Should return matching students")
    void testSearchStudentsByName() throws Exception {
        mockMvc.perform(get("/api/students/search?name=Rahul"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$.data[0].name", containsString("Rahul")));
    }

    @Test
    @DisplayName("GET /api/students/department/Computer Science - Should return CSE students")
    void testGetStudentsByDepartment() throws Exception {
        mockMvc.perform(get("/api/students/department/Computer Science"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    @DisplayName("GET /api/students/status/PLACED - Should return placed students")
    void testGetStudentsByStatus() throws Exception {
        mockMvc.perform(get("/api/students/status/PLACED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$.data[0].placementStatus", is("PLACED")));
    }
}
