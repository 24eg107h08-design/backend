package com.talentflow.backend.service.impl;

import com.talentflow.backend.exception.ResourceNotFoundException;
import com.talentflow.backend.model.Role;
import com.talentflow.backend.model.User;
import com.talentflow.backend.repository.UserRepository;
import com.talentflow.backend.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public User createUser(User user) {
        if (user.getAvatar() == null || user.getAvatar().isBlank()) {
            user.setAvatar("https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=150");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        if (userDetails.getName() != null) user.setName(userDetails.getName());
        if (userDetails.getEmail() != null) user.setEmail(userDetails.getEmail());
        if (userDetails.getRole() != null) user.setRole(userDetails.getRole());
        if (userDetails.getDepartment() != null) user.setDepartment(userDetails.getDepartment());
        if (userDetails.getTitle() != null) user.setTitle(userDetails.getTitle());
        if (userDetails.getPhone() != null) user.setPhone(userDetails.getPhone());
        if (userDetails.getLocation() != null) user.setLocation(userDetails.getLocation());
        if (userDetails.getBio() != null) user.setBio(userDetails.getBio());
        if (userDetails.getStatus() != null) user.setStatus(userDetails.getStatus());
        return userRepository.save(user);
    }

    @Override
    public User toggleUserStatus(Long id) {
        User user = getUserById(id);
        user.setStatus("Active".equalsIgnoreCase(user.getStatus()) ? "Inactive" : "Active");
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}
