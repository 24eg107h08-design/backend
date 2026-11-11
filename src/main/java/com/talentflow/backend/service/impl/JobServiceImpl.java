package com.talentflow.backend.service.impl;

import com.talentflow.backend.exception.ResourceNotFoundException;
import com.talentflow.backend.model.Job;
import com.talentflow.backend.repository.JobRepository;
import com.talentflow.backend.service.JobService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Job> getJobsByStatus(String status) {
        return jobRepository.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Job> getJobsByDepartment(String department) {
        return jobRepository.findByDepartmentIgnoreCase(department);
    }

    @Override
    public Job createJob(Job job) {
        if (job.getApplicantsCount() == null) {
            job.setApplicantsCount(0);
        }
        if (job.getStatus() == null || job.getStatus().isBlank()) {
            job.setStatus("Open");
        }
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Long id, Job jobDetails) {
        Job job = getJobById(id);
        if (jobDetails.getTitle() != null) job.setTitle(jobDetails.getTitle());
        if (jobDetails.getDepartment() != null) job.setDepartment(jobDetails.getDepartment());
        if (jobDetails.getLocation() != null) job.setLocation(jobDetails.getLocation());
        if (jobDetails.getType() != null) job.setType(jobDetails.getType());
        if (jobDetails.getSalary() != null) job.setSalary(jobDetails.getSalary());
        if (jobDetails.getDescription() != null) job.setDescription(jobDetails.getDescription());
        if (jobDetails.getRequirements() != null) job.setRequirements(jobDetails.getRequirements());
        if (jobDetails.getStatus() != null) job.setStatus(jobDetails.getStatus());
        return jobRepository.save(job);
    }

    @Override
    public Job toggleJobStatus(Long id) {
        Job job = getJobById(id);
        job.setStatus("Open".equalsIgnoreCase(job.getStatus()) ? "Paused" : "Open");
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long id) {
        Job job = getJobById(id);
        jobRepository.delete(job);
    }

    @Override
    public void incrementApplicantCount(Long jobId) {
        if (jobId != null) {
            jobRepository.findById(jobId).ifPresent(job -> {
                job.setApplicantsCount(job.getApplicantsCount() + 1);
                jobRepository.save(job);
            });
        }
    }
}
