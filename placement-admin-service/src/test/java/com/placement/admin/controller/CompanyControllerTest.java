package com.placement.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.placement.admin.dto.company.CompanyRequestDTO;
import com.placement.admin.entity.enums.CompanyStatus;
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
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/companies - Should create company successfully")
    void testCreateCompanySuccess() throws Exception {
        CompanyRequestDTO request = new CompanyRequestDTO(
                "Acme Corp", "contact@acme.com", "+91 8877665544", "https://acme.com",
                "Software & Cloud", "Delhi", "Leading software product enterprise", CompanyStatus.ACTIVE
        );

        mockMvc.perform(post("/api/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("Company created successfully")))
                .andExpect(jsonPath("$.data.companyName", is("Acme Corp")))
                .andExpect(jsonPath("$.data.status", is("ACTIVE")));
    }

    @Test
    @DisplayName("GET /api/companies - Should return list of companies")
    void testGetAllCompanies() throws Exception {
        mockMvc.perform(get("/api/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Companies retrieved successfully")))
                .andExpect(jsonPath("$.data", hasSize(greaterThanOrEqualTo(5))));
    }

    @Test
    @DisplayName("GET /api/companies/{id} - Should return 404 for invalid company ID")
    void testGetCompanyByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/companies/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", containsString("Company with ID 9999 not found")));
    }

    @Test
    @DisplayName("GET /api/companies/search?name=Tech - Should return matching companies")
    void testSearchCompaniesByName() throws Exception {
        mockMvc.perform(get("/api/companies/search?name=Tech"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$.data[0].companyName", containsString("Tech")));
    }

    @Test
    @DisplayName("GET /api/companies/status/ACTIVE - Should return active companies")
    void testGetCompaniesByStatus() throws Exception {
        mockMvc.perform(get("/api/companies/status/ACTIVE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$.data[0].status", is("ACTIVE")));
    }
}
