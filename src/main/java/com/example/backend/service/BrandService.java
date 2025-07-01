package com.example.backend.service;

import com.example.backend.dto.request.BrandRequest;
import com.example.backend.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAllBrands();
    Brand getBrandById(Long id);
    Brand createBrand(BrandRequest request);
    Brand updateBrand(Long id, BrandRequest request);
    void deleteBrand(Long id);
}

