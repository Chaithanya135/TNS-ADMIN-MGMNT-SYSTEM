package com.placement.admin.controller;

import com.placement.admin.dto.dashboard.DashboardResponseDTO;
import com.placement.admin.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponseDTO> getDashboardStatistics() {
        DashboardResponseDTO dashboardData = dashboardService.getDashboardStatistics();
        return ResponseEntity.ok(dashboardData);
    }
}
