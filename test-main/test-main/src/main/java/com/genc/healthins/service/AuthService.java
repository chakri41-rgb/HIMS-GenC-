package com.genc.healthins.service;

import java.util.Optional;

import com.genc.healthins.entity.User;

public interface AuthService {
    User register(User user);
    Optional<User> findByUsername(String username);
}
