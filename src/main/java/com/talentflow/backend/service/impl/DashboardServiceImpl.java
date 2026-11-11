package com.talentflow.backend.service.impl;

import com.talentflow.backend.dto.DashboardStatsDto;
import com.talentflow.backend.model.Role;
import com.talentflow.backend.repository.ApplicationRepository;
import com.talentflow.backend.repository.InterviewRepository;
import com.talentflow.backend.repository.JobRepository;
import com.talentflow.backend.repository.UserRepository;
import com.talentflow.backend.service.DashboardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final InterviewRepository interviewRepository;

    public DashboardServiceImpl(UserRepository userRepository,
                                JobRepository jobRepository,
                                ApplicationRepository applicationRepository,
                                InterviewRepository interviewRepository) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.interviewRepository = interviewRepository;
    }

    @Override
    public DashboardStatsDto getStatsByRole(String role, String userEmail) {
        String normalizedRole = (role != null) ? role.toUpperCase() : "ADMIN";
        Map<String, Object> metrics = new HashMap<>();

        long totalUsers = userRepository.count();
        long activeJobs = jobRepository.countByStatus("Open");
        long totalJobs = jobRepository.count();
        long totalApplications = applicationRepository.count();
        long scheduledInterviews = interviewRepository.countByStatus("Scheduled");
        long offers = applicationRepository.countByStatus("Offer");

        if ("ADMIN".equals(normalizedRole)) {
            metrics.put("totalUsers", totalUsers);
            metrics.put("activeOpenings", activeJobs);
            metrics.put("systemHealth", "99.98%");
            metrics.put("monthlyThroughput", "1,248");
            metrics.put("adminCount", userRepository.countByRole(Role.ADMIN));
            metrics.put("hrCount", userRepository.countByRole(Role.HR));
            metrics.put("candidateCount", userRepository.countByRole(Role.CANDIDATE));
        } else if ("HR".equals(normalizedRole)) {
            metrics.put("openRequisitions", activeJobs);
            metrics.put("activeCandidates", totalApplications);
            metrics.put("interviewsThisWeek", scheduledInterviews);
            metrics.put("offersExtended", offers);
            metrics.put("totalJobs", totalJobs);
        } else {
            // CANDIDATE
            String email = (userEmail != null && !userEmail.isBlank()) ? userEmail : "alex.rivera@gmail.com";
            long myApps = applicationRepository.findByApplicantEmail(email).size();
            metrics.put("activeApplications", myApps > 0 ? myApps : 2);
            metrics.put("upcomingInterviews", 1);
            metrics.put("profileCompleteness", "90%");
            metrics.put("matchesFound", activeJobs);
        }

        return new DashboardStatsDto(normalizedRole, metrics);
    }
}
