package com.talentflow.backend.service.impl;

import com.talentflow.backend.exception.ResourceNotFoundException;
import com.talentflow.backend.model.Interview;
import com.talentflow.backend.repository.InterviewRepository;
import com.talentflow.backend.service.InterviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;

    public InterviewServiceImpl(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Interview> getAllInterviews() {
        return interviewRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Interview getInterviewById(Long id) {
        return interviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Interview> getInterviewsByStatus(String status) {
        return interviewRepository.findByStatus(status);
    }

    @Override
    public Interview scheduleInterview(Interview interview) {
        if (interview.getStatus() == null || interview.getStatus().isBlank()) {
            interview.setStatus("Scheduled");
        }
        if (interview.getLink() == null || interview.getLink().isBlank()) {
            interview.setLink("https://meet.google.com/talentflow-interview-" + System.currentTimeMillis());
        }
        return interviewRepository.save(interview);
    }

    @Override
    public Interview updateStatus(Long id, String status) {
        Interview interview = getInterviewById(id);
        interview.setStatus(status);
        return interviewRepository.save(interview);
    }

    @Override
    public void cancelInterview(Long id) {
        Interview interview = getInterviewById(id);
        interviewRepository.delete(interview);
    }
}
