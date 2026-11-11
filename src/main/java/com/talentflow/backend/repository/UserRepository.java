package com.talentflow.backend.repository;

import com.talentflow.backend.model.Role;
import com.talentflow.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    List<User> findByStatus(String status);
    long countByRole(Role role);
    long countByStatus(String status);
}
