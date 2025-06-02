package com.example.backend.service;

import com.example.backend.dto.user.UserCreationRequest;
import com.example.backend.entity.User;
import com.example.backend.reponsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User createUser(UserCreationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setDob(request.getDob());
        user.setAddress(request.getAddress());
        return userRepository.save(user);
    }

    public List<User> getUser(){
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }
}