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

//    @PostMapping("/login")
//    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginRequest loginRequest) {
//        JwtAuthResponse jwtResponse = authService.login(loginRequest);
//        return ResponseEntity.ok(jwtResponse);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            JwtAuthResponse jwtAuthResponse = authService.login(loginRequest);
            return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthResponse> signup(@RequestBody SignupRequest signupRequest) throws Exception {
        JwtAuthResponse jwtResponse = authService.signUp(signupRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtAuthResponse> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        JwtAuthResponse jwtResponse = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(jwtResponse);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String bearerToken) {
        String jwt = bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : bearerToken;
        User user = authService.findUserByJwt(jwt);
        return ResponseEntity.ok(user);
    }
}
