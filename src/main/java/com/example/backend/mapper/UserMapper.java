package com.example.backend.mapper;

import com.example.backend.dto.response.UserResponse;
import com.example.backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "created_at", target = "createdAt")
    UserResponse toResponseDTO(User user);
    List<UserResponse> toResponseList(List<User> users);
}