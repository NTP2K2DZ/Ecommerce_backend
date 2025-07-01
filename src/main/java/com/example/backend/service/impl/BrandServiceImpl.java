package com.example.backend.service.impl;

import com.example.backend.dto.request.BrandRequest;
import com.example.backend.entity.Brand;
import com.example.backend.exception.BadRequestException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.reponsitory.BrandRepository;
import com.example.backend.reponsitory.ProductRepository;
import com.example.backend.service.BrandService;
import com.example.backend.util.SlugUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService{

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository, ProductRepository productRepository) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + id));
    }

    @Override
    @Transactional
    public Brand createBrand(BrandRequest request) {
        String name = request.getName();
        String slug = SlugUtil.toBrandSlug(name);

        if (brandRepository.existsByName(name)) {
            throw new BadRequestException("Brand name already exists: " + name);
        }

        if (brandRepository.existsBySlug(slug)) {
            throw new BadRequestException("Brand slug already exists: " + slug);
        }

        Brand brand = new Brand();
        brand.setName(name);
        brand.setLogoUrl(request.getLogoUrl());
        brand.setSlug(slug);

        return brandRepository.save(brand);
    }

    @Override
    @Transactional
    public Brand updateBrand(Long id, BrandRequest request) {
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
        existingBrand.setLogoUrl(request.getLogoUrl());

        return brandRepository.save(existingBrand);
    }

    @Override
    @Transactional
    public void deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + id));

        if (productRepository.existsByBrandId(id)) {
            throw new BadRequestException("Cannot delete brand because it is associated with existing products");
        }

        brandRepository.delete(brand);
    }
}
