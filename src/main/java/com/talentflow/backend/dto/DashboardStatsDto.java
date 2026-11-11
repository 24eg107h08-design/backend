package com.talentflow.backend.dto;

import java.util.Map;

public class DashboardStatsDto {
    private String role;
    private Map<String, Object> metrics;

    public DashboardStatsDto() {
    }

    public DashboardStatsDto(String role, Map<String, Object> metrics) {
        this.role = role;
        this.metrics = metrics;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Map<String, Object> getMetrics() {
        return metrics;
    }

    public void setMetrics(Map<String, Object> metrics) {
        this.metrics = metrics;
    }
}
