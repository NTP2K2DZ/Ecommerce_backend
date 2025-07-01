package com.example.backend.service;

import com.example.backend.dto.request.UserRequest;
import com.example.backend.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    List<UserResponse> getUser();
    UserResponse getUserById(long id);
    UserResponse updateUser(long id, UserRequest request);
}
