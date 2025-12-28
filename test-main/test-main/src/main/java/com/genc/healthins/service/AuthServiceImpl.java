package com.genc.healthins.service;

import com.genc.healthins.dao.UserRepository;
import com.genc.healthins.entity.Role;
import com.genc.healthins.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        // Allow CUSTOMER and AGENT roles from registration; default to CUSTOMER if unspecified or invalid
        if(user.getRole() == null) {
            user.setRole(Role.CUSTOMER);
        } else {
            if(user.getRole() != Role.CUSTOMER && user.getRole() != Role.AGENT) {
                user.setRole(Role.CUSTOMER);
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
