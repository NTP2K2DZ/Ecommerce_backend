package com.example.backend.controller;

import com.example.backend.dto.request.AddressRequest;
import com.example.backend.dto.response.AddressResponse;
import com.example.backend.service.AddressService;
import com.example.backend.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/address")
public class AddressController {

    private final AuthService authService;
    private final AddressService addressService;

    public AddressController(AuthService authService, AddressService addressService) {
        this.authService = authService;
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressResponse> createAddress(
            @RequestBody AddressRequest request,
            @RequestHeader("Authorization") String jwt) {
        Long userId = authService.findUserByJwt(jwt).getId();
        AddressResponse response = addressService.createAddress(userId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAddresses(@RequestHeader("Authorization") String jwt) {
        Long userId = authService.findUserByJwt(jwt).getId();
        List<AddressResponse> responses = addressService.getAddressByUserId(userId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long addressId) {
        AddressResponse response = addressService.getAddressById(addressId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long addressId,
                                                         @RequestBody AddressRequest request) {
        AddressResponse response = addressService.updateAddress(addressId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.ok("Address deleted successfully!");
    }
}
