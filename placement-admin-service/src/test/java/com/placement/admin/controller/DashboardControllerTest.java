package com.placement.admin.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/admin/dashboard - Should calculate and return accurate dynamic metrics")
    void testGetDashboardStatistics() throws Exception {
        mockMvc.perform(get("/api/admin/dashboard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalStudents", is(10)))
                .andExpect(jsonPath("$.totalCompanies", is(5)))
                .andExpect(jsonPath("$.totalPlacementDrives", is(5)))
                .andExpect(jsonPath("$.totalApplications", is(15)))
                .andExpect(jsonPath("$.placedStudents", is(4)))
                .andExpect(jsonPath("$.unplacedStudents", is(6)))
                .andExpect(jsonPath("$.selectedApplications", is(4)))
                .andExpect(jsonPath("$.rejectedApplications", is(3)))
                .andExpect(jsonPath("$.shortlistedApplications", is(4)))
                .andExpect(jsonPath("$.upcomingDrives", is(1)))
                .andExpect(jsonPath("$.openDrives", is(3)));
    }
}
