package com.example.backend.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserCreationRequest {
    private String username;
    private String password;
    private String email;
    private String phone;
    private LocalDate dob;
    private String address;
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime created_at;
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDateTime getCreate_at() {
        return created_at;
    }
}
