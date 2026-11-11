package com.talentflow.backend.service;

import com.talentflow.backend.model.Job;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();
    Job getJobById(Long id);
    List<Job> getJobsByStatus(String status);
    List<Job> getJobsByDepartment(String department);
    Job createJob(Job job);
    Job updateJob(Long id, Job jobDetails);
    Job toggleJobStatus(Long id);
    void deleteJob(Long id);
    void incrementApplicantCount(Long jobId);
}
