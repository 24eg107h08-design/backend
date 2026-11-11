package com.talentflow.backend.service;

import com.talentflow.backend.dto.DashboardStatsDto;

public interface DashboardService {
    DashboardStatsDto getStatsByRole(String role, String userEmail);
}
