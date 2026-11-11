package com.talentflow.backend.controller;

import com.talentflow.backend.dto.ApiResponse;
import com.talentflow.backend.model.Job;
import com.talentflow.backend.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Job>>> getAllJobs(@RequestParam(required = false) String department,
                                                             @RequestParam(required = false) String status) {
        List<Job> jobs;
        if (department != null && !department.isBlank() && !"All".equalsIgnoreCase(department)) {
            jobs = jobService.getJobsByDepartment(department);
        } else if (status != null && !status.isBlank()) {
            jobs = jobService.getJobsByStatus(status);
        } else {
            jobs = jobService.getAllJobs();
        }
        return ResponseEntity.ok(ApiResponse.success("Jobs retrieved successfully", jobs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Job>> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        return ResponseEntity.ok(ApiResponse.success("Job retrieved successfully", job));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Job>> createJob(@Valid @RequestBody Job job) {
        Job created = jobService.createJob(job);
        return new ResponseEntity<>(ApiResponse.success("Job requisition published successfully", created), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Job>> updateJob(@PathVariable Long id, @RequestBody Job job) {
        Job updated = jobService.updateJob(id, job);
        return ResponseEntity.ok(ApiResponse.success("Job updated successfully", updated));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Job>> toggleStatus(@PathVariable Long id) {
        Job updated = jobService.toggleJobStatus(id);
        return ResponseEntity.ok(ApiResponse.success("Job status toggled successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok(ApiResponse.success("Job deleted successfully", null));
    }
}
