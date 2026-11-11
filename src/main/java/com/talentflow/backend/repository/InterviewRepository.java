package com.talentflow.backend.repository;

import com.talentflow.backend.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByStatus(String status);
    List<Interview> findByCandidateName(String candidateName);
    long countByStatus(String status);
}
