package com.example.backend.service;

import com.example.backend.dto.brand.BrandCreationRequest;
import com.example.backend.dto.brand.BrandUpdateRequest;
import com.example.backend.entity.Brand;
import com.example.backend.exception.BadRequestException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.reponsitory.BrandRepository;
import com.example.backend.reponsitory.ProductRepository;
import com.example.backend.util.SlugUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    public BrandService(BrandRepository brandRepository, ProductRepository productRepository) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + id));
    }

    @Transactional
    public Brand createBrand(BrandCreationRequest request) {
        String name = request.getName();
        String slug = SlugUtil.toBrandSlug(request.getName());

        if (brandRepository.existsByName(name)) {
            throw new BadRequestException("Brand name already exists: " + name);
        }

        if (brandRepository.existsBySlug(slug)) {
            throw new BadRequestException("Brand slug already exists: " + slug);
        }
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setLogoUrl(request.getLogoUrl());
        brand.setSlug(slug);
        return brandRepository.save(brand);
    }

    @Transactional
    public Brand updateBrand(Long id, BrandUpdateRequest request) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + id));

        String newName = request.getName();
        String newSlug = SlugUtil.toBrandSlug(newName);

        if (!newName.equalsIgnoreCase(existingBrand.getName())
                && brandRepository.existsByNameAndIdNot(newName, id)) {
            throw new BadRequestException("Brand name already exists: " + newName);
        }

        if (!newSlug.equalsIgnoreCase(existingBrand.getSlug())
                && brandRepository.existsBySlugAndIdNot(newSlug, id)) {
            throw new BadRequestException("Brand slug already exists: " + newSlug);
        }

        existingBrand.setName(newName);
        existingBrand.setSlug(newSlug);
        existingBrand.setLogoUrl(request.getLogoUrl()); // chỉ thay đổi ảnh cũng OK

        return brandRepository.save(existingBrand);
    }

    @Transactional
    public void deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + id));

        boolean hasProducts = productRepository.existsByBrandId(id);
        if (hasProducts) {
            throw new BadRequestException("Cannot delete brand because it is associated with existing products");
        }

        brandRepository.delete(brand);
    }
}
