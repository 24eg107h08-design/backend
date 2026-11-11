package com.talentflow.backend.service;

import com.talentflow.backend.model.Role;
import com.talentflow.backend.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getUsersByRole(Role role);
    User createUser(User user);
    User updateUser(Long id, User userDetails);
    User toggleUserStatus(Long id);
    void deleteUser(Long id);
}
