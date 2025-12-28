package com.genc.healthins.service;

import com.genc.healthins.dao.UserRepository;
import com.genc.healthins.exception.ResourceNotFoundException;
import com.genc.healthins.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        if(user.getPassword() != null) user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() { return userRepository.findAll(); }

    @Override
    public User updateUser(Integer id, User user) {
        Objects.requireNonNull(id, "id must not be null");
        User existing = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(user.getUsername() != null) existing.setUsername(user.getUsername());
        if(user.getEmail() != null) existing.setEmail(user.getEmail());
        if(user.getRole() != null) existing.setRole(user.getRole());
        if(user.getPassword() != null) existing.setPassword(passwordEncoder.encode(user.getPassword()));
        java.util.Objects.requireNonNull(existing, "existing user must not be null");
        return userRepository.save(existing);
    }

    @Override
    public void deleteUser(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        userRepository.deleteById(id);
    }
}
