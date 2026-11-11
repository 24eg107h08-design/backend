package com.talentflow.backend.service;

import com.talentflow.backend.model.Interview;

import java.util.List;

public interface InterviewService {
    List<Interview> getAllInterviews();
    Interview getInterviewById(Long id);
    List<Interview> getInterviewsByStatus(String status);
    Interview scheduleInterview(Interview interview);
    Interview updateStatus(Long id, String status);
    void cancelInterview(Long id);
}
