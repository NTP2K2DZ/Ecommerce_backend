package com.example.backend.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserResponse {
    private String username;
    private String email;
    private String phone;
    private LocalDate dob;
    private String address;
    private LocalDateTime createdAt;

    public UserResponse() {}

    public UserResponse(String username, String email, String phone, LocalDate dob, String address, LocalDateTime createdAt) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
        this.createdAt = createdAt;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
