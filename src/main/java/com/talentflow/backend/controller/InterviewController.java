package com.talentflow.backend.controller;

import com.talentflow.backend.dto.ApiResponse;
import com.talentflow.backend.model.Interview;
import com.talentflow.backend.service.InterviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Interview>>> getAllInterviews(@RequestParam(required = false) String status) {
        List<Interview> interviews = (status != null && !status.isBlank())
                ? interviewService.getInterviewsByStatus(status)
                : interviewService.getAllInterviews();
        return ResponseEntity.ok(ApiResponse.success("Interviews retrieved successfully", interviews));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Interview>> getInterviewById(@PathVariable Long id) {
        Interview interview = interviewService.getInterviewById(id);
        return ResponseEntity.ok(ApiResponse.success("Interview retrieved successfully", interview));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Interview>> scheduleInterview(@Valid @RequestBody Interview interview) {
        Interview scheduled = interviewService.scheduleInterview(interview);
        return new ResponseEntity<>(ApiResponse.success("Interview scheduled successfully", scheduled), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Interview>> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String newStatus = payload.get("status");
        Interview updated = interviewService.updateStatus(id, newStatus);
        return ResponseEntity.ok(ApiResponse.success("Interview status updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> cancelInterview(@PathVariable Long id) {
        interviewService.cancelInterview(id);
        return ResponseEntity.ok(ApiResponse.success("Interview cancelled successfully", null));
    }
}
