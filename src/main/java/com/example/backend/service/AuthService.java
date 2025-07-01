package com.example.backend.service;

//import com.example.backend.dto.request.ChangePasswordRequestDto;
import com.example.backend.dto.request.LoginRequest;
import com.example.backend.dto.request.SignupRequest;
import com.example.backend.dto.response.JwtAuthResponse;
import com.example.backend.entity.User;
//import org.springframework.security.oauth2.core.user.OAuth2User;

public interface AuthService {
    JwtAuthResponse login(LoginRequest loginRequest);
    JwtAuthResponse signUp(SignupRequest signupRequest) throws Exception;
    JwtAuthResponse refreshToken(String refreshToken);
    User loginReturnUser(LoginRequest loginRequest);
    User signupReturnUser(SignupRequest signupRequest);
    User findUserByJwt(String jwt);
//    String forgotPassword(String email);
}
