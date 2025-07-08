package com.example.backend.controller;
import com.example.backend.dto.request.LoginRequest;
import com.example.backend.dto.request.SignupRequest;
import com.example.backend.dto.response.JwtAuthResponse;
import com.example.backend.entity.User;
import com.example.backend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            JwtAuthResponse jwtAuthResponse = authService.login(loginRequest);
            return ResponseEntity.ok(jwtAuthResponse);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed. Please try again.");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        try {
            JwtAuthResponse jwtResponse = authService.signUp(signupRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(jwtResponse);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Signup failed. Please try again.");
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        try {
            JwtAuthResponse jwtResponse = authService.refreshToken(refreshToken);
            return ResponseEntity.ok(jwtResponse);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid refresh token.");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String bearerToken) {
        try {
            String jwt = bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : bearerToken;
            User user = authService.findUserByJwt(jwt);
            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
        }
    }
}
