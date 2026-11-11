package com.talentflow.backend.repository;

import com.talentflow.backend.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByStatus(String status);
    List<Job> findByDepartmentIgnoreCase(String department);
    long countByStatus(String status);
}
