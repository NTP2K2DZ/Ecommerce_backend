package com.example.backend.service;

import com.example.backend.dto.request.AddressRequest;
import com.example.backend.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {
    AddressResponse createAddress(Long userId,AddressRequest addressRequest);
    AddressResponse updateAddress(Long addressId, AddressRequest addressRequest);
    void deleteAddress(Long addressId);
    List<AddressResponse> getAddressByUserId(Long userId);
    AddressResponse getAddressById(Long id);
}