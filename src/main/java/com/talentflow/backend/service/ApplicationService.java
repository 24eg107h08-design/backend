package com.talentflow.backend.service;

import com.talentflow.backend.model.Application;

import java.util.List;

public interface ApplicationService {
    List<Application> getAllApplications();
    Application getApplicationById(Long id);
    List<Application> getApplicationsByCandidateEmail(String email);
    List<Application> getApplicationsByJobId(Long jobId);
    List<Application> getApplicationsByStatus(String status);
    Application submitApplication(Application application);
    Application updateStage(Long id, String newStatus);
    void deleteApplication(Long id);
}
