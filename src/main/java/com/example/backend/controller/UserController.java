package com.example.backend.controller;

import com.example.backend.dto.user.UserCreationRequest;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    User createUser(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    List<User> getUser() {
        return userService.getUser();
    }
    @GetMapping("/{userId}")
    User getUserByID(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }
}
