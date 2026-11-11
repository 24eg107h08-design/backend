package com.talentflow.backend.service.impl;

import com.talentflow.backend.exception.ResourceNotFoundException;
import com.talentflow.backend.model.Application;
import com.talentflow.backend.repository.ApplicationRepository;
import com.talentflow.backend.service.ApplicationService;
import com.talentflow.backend.service.JobService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobService jobService;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, JobService jobService) {
        this.applicationRepository = applicationRepository;
        this.jobService = jobService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Application> getApplicationsByCandidateEmail(String email) {
        return applicationRepository.findByApplicantEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Application> getApplicationsByJobId(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Application> getApplicationsByStatus(String status) {
        return applicationRepository.findByStatus(status);
    }

    @Override
    public Application submitApplication(Application application) {
        if (application.getAppliedDate() == null || application.getAppliedDate().isBlank()) {
            application.setAppliedDate(LocalDate.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
        }
        if (application.getStatus() == null || application.getStatus().isBlank()) {
            application.setStatus("Under Review");
            application.setStageNumber(1);
        }
        if (application.getMatchScore() == null || application.getMatchScore().isBlank()) {
            application.setMatchScore("88%");
        }
        if (application.getNotes() == null || application.getNotes().isBlank()) {
            application.setNotes("Application received via online candidate portal.");
        }

        Application saved = applicationRepository.save(application);

        // Increment count on related job
        if (application.getJobId() != null) {
            jobService.incrementApplicantCount(application.getJobId());
        }

        return saved;
    }

    @Override
    public Application updateStage(Long id, String newStatus) {
        Application application = getApplicationById(id);
        application.setStatus(newStatus);

        switch (newStatus.toLowerCase()) {
            case "applied":
            case "under review":
                application.setStageNumber(1);
                application.setNotes("Resume passed initial ATS screening.");
                break;
            case "interview":
                application.setStageNumber(3);
                application.setNotes("Selected for technical deep-dive interview session.");
                break;
            case "offer":
                application.setStageNumber(4);
                application.setNotes("Offer letter extended. Awaiting candidate formal signoff.");
                break;
            case "rejected":
                application.setStageNumber(1);
                application.setNotes("Candidate qualifications do not match opening requirements at this time.");
                break;
            default:
                break;
        }

        return applicationRepository.save(application);
    }

    @Override
    public void deleteApplication(Long id) {
        Application app = getApplicationById(id);
        applicationRepository.delete(app);
    }
}
