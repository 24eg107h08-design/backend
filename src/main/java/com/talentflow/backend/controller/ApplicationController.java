package com.talentflow.backend.controller;

import com.talentflow.backend.dto.ApiResponse;
import com.talentflow.backend.model.Application;
import com.talentflow.backend.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Application>>> getAllApplications(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Long jobId,
            @RequestParam(required = false) String status) {

        List<Application> applications;
        if (email != null && !email.isBlank()) {
            applications = applicationService.getApplicationsByCandidateEmail(email);
        } else if (jobId != null) {
            applications = applicationService.getApplicationsByJobId(jobId);
        } else if (status != null && !status.isBlank()) {
            applications = applicationService.getApplicationsByStatus(status);
        } else {
            applications = applicationService.getAllApplications();
        }
        return ResponseEntity.ok(ApiResponse.success("Applications retrieved successfully", applications));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Application>> getApplicationById(@PathVariable Long id) {
        Application app = applicationService.getApplicationById(id);
        return ResponseEntity.ok(ApiResponse.success("Application retrieved successfully", app));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Application>> submitApplication(@Valid @RequestBody Application application) {
        Application submitted = applicationService.submitApplication(application);
        return new ResponseEntity<>(ApiResponse.success("Application submitted successfully", submitted), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/stage")
    public ResponseEntity<ApiResponse<Application>> updateStage(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String newStatus = payload.get("status");
        if (newStatus == null || newStatus.isBlank()) {
            newStatus = payload.get("stage");
        }
        Application updated = applicationService.updateStage(id, newStatus);
        return ResponseEntity.ok(ApiResponse.success("Application stage updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.ok(ApiResponse.success("Application deleted successfully", null));
    }
}
