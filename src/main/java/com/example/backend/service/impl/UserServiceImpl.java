//package com.example.backend.service.impl;
//
//import com.example.backend.dto.request.UserRequest;
//import com.example.backend.dto.response.UserResponse;
//import com.example.backend.entity.User;
//import com.example.backend.mapper.UserMapper;
//import com.example.backend.reponsitory.UserRepository;
//import com.example.backend.service.UserService;
//import jakarta.transaction.Transactional;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final UserMapper userMapper;
//
//    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.userMapper = userMapper;
//    }
//
//    @Override
//    public List<UserResponse> getUser() {
//        List<User> users = userRepository.findAll();
//        return userMapper.toResponseList(users);
//    }
//
//    @Override
//    public UserResponse getUserById(long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        return userMapper.toResponseDTO(user);
//    }
//}
