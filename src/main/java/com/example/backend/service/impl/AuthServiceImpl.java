package com.example.backend.service.impl;

import com.example.backend.dto.request.LoginRequest;
import com.example.backend.dto.request.SignupRequest;
import com.example.backend.dto.response.JwtAuthResponse;
import com.example.backend.entity.User;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.reponsitory.UserRepository;
import com.example.backend.service.AuthService;
import com.example.backend.service.CustomUserDetailsService;
import com.example.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            CustomUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            JwtUtil jwtUtil
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public JwtAuthResponse login(LoginRequest loginRequest) {
        System.out.println(loginRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        System.out.println(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtil.generateToken(authentication);
        String refreshToken = jwtUtil.generateRefreshToken(authentication);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setTokenType("Bearer");
        return response;
    }

    @Override
    public JwtAuthResponse signUp(SignupRequest signupRequest) {
        // Kiểm tra username và email đã tồn tại
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        // Tạo user mới
        User newUser = new User();
        newUser.setUsername(signupRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPhone(signupRequest.getPhone());

        userRepository.save(newUser);

        // Đăng nhập tự động sau khi đăng ký
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signupRequest.getUsername(),
                        signupRequest.getPassword()
                )
        );

        // Tạo JWT token
        String accessToken = jwtUtil.generateToken(authentication);
        String refreshToken = jwtUtil.generateRefreshToken(authentication);

        // Tạo response
        JwtAuthResponse response = new JwtAuthResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setTokenType("Bearer");

        return response;
    }

    @Override
    public JwtAuthResponse refreshToken(String refreshToken) {
        // Check for valid tokens
        if (!jwtUtil.validateToken(refreshToken)) {
            throw new RuntimeException("Refresh token is invalid or expired");
        }

        // Extract username from refresh token
        String username = jwtUtil.getUsernameFromToken(refreshToken);

        // Download user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Create temporary Authentication object
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        // Generate new access token
        String newAccessToken = jwtUtil.generateToken(authentication);


        // Return JWT response
        JwtAuthResponse response = new JwtAuthResponse();
        response.setAccessToken(newAccessToken);
        response.setRefreshToken(refreshToken);
        response.setTokenType("Bearer");
        return response;
    }



    @Override
    public User loginReturnUser(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        return userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User signupReturnUser(SignupRequest signupRequest) {
        User newUser = new User();
        newUser.setUsername(signupRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPhone(signupRequest.getPhone());
        return userRepository.save(newUser);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String username = jwtUtil.getUsernameFromToken(jwt);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

}
