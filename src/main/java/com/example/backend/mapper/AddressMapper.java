package com.example.backend.mapper;

import com.example.backend.dto.response.AddressResponse;
import com.example.backend.entity.Address;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressResponse toResponseDTO(Address address);
    List<AddressResponse> toResponseList(List<Address> addresses);
}
