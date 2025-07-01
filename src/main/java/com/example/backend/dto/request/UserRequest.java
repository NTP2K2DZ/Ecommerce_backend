package com.example.backend.dto.request;

import java.time.LocalDate;

public class UserRequest {
    private String username;
    private String password;
    private String email;
    private String phone;
    private LocalDate dob;
    private String address;

    public UserRequest() {}

    public UserRequest(String username, String password, String email, String phone, LocalDate dob, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
