package com.talentflow.backend.repository;

import com.talentflow.backend.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByApplicantEmail(String applicantEmail);
    List<Application> findByJobId(Long jobId);
    List<Application> findByStatus(String status);
    long countByStatus(String status);
}
