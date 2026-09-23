package com.placement.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getRootInfo() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("service", "Placement Management System - Admin Service");
        response.put("status", "UP");
        response.put("message", "Admin Service backend is running successfully!");

        Map<String, String> endpoints = new LinkedHashMap<>();
        endpoints.put("dashboard", "/api/admin/dashboard");
        endpoints.put("admins", "/api/admins");
        endpoints.put("students", "/api/students");
        endpoints.put("companies", "/api/companies");
        endpoints.put("drives", "/api/drives");
        endpoints.put("applications", "/api/applications");
        endpoints.put("h2_console", "/h2-console");

        response.put("availableEndpoints", endpoints);
        response.put("documentation", "See README.md and Placement_Admin_Service_Postman_Collection.json");

        return ResponseEntity.ok(response);
    }
}
