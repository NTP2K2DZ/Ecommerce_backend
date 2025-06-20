package com.example.backend.service;

import com.example.backend.dto.product_variant.ProductVariantCreationRequest;
import com.example.backend.dto.product_variant.ProductVariantResponse;
import com.example.backend.dto.product_variant.ProductVariantUpdateRequest;
import com.example.backend.entity.*;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.ProductVariantMapper;
import com.example.backend.reponsitory.ProductOptionValueRepository;
import com.example.backend.reponsitory.ProductRepository;
import com.example.backend.reponsitory.ProductVariantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;
    private final ProductOptionValueRepository productOptionValueRepository;

    public ProductVariantService(ProductVariantRepository productVariantRepository,
                                 ProductRepository productRepository,
                                 ProductOptionValueRepository productOptionValueRepository) {
        this.productVariantRepository = productVariantRepository;
        this.productRepository = productRepository;
        this.productOptionValueRepository = productOptionValueRepository;
    }

    public List<ProductVariantResponse> getAllVariants() {
        return productVariantRepository.findAll().stream()
                .map(ProductVariantMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ProductVariantResponse> getVariantsByProductId(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));

        List<ProductVariant> variants = productVariantRepository.findByProductId(productId);
        return ProductVariantMapper.toResponseList(variants);
    }


    @Transactional
    public ProductVariantResponse createVariant(ProductVariantCreationRequest request) {
        // Find product
        Long productId = request.getProductId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));

        // Get option values from ID
        List<ProductOptionValue> optionValues = productOptionValueRepository.findAllById(request.getOptionValueIds());

        // create variant
        ProductVariant variant = ProductVariantMapper.toEntityCreate(request, product);

        // Handle image
        if (request.getImageUrl() != null) {
            List<ProductVariantImage> images = request.getImageUrl().stream().map(url -> {
                ProductVariantImage image = new ProductVariantImage();
                image.setImageUrl(url);
                image.setVariant(variant);
                return image;
            }).collect(Collectors.toList());
            variant.setImages(images);
        }

        List<ProductVariantOptionValue> variantOptionValues = optionValues.stream().map(optionValue -> {
            ProductVariantOptionValue productVariantOptionValue = new ProductVariantOptionValue();
            productVariantOptionValue.setOptionValue(optionValue);
            productVariantOptionValue.setVariant(variant);
            return productVariantOptionValue;
        }).collect(Collectors.toList());
        variant.setOptionValues(variantOptionValues);

        // Lưu vào DB
        ProductVariant saved = productVariantRepository.save(variant);
        return ProductVariantMapper.toResponseDTO(saved);
    }

    @Transactional
    public ProductVariantResponse updateVariant(Long variantId, ProductVariantUpdateRequest request) {
        // Find variant
        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() -> new ResourceNotFoundException("Variant not found with id " + variantId));

        // Find product if changed
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + request.getProductId()));

        //
        if (request.getSlug() != null) {
            variant.setSlug(request.getSlug());
        }
        if (request.getPrice() != null) {
            variant.setPrice(request.getPrice());
        }
        if (request.getQuantity() != null) {
            variant.setQuantity(request.getQuantity());
        }
        if (product != null && !product.equals(variant.getProduct())) {
            variant.setProduct(product);
        }

        // Update image if changed
        if (request.getImageUrl() != null) {
            List<ProductVariantImage> currentImages = variant.getImages();
            if (currentImages == null) {
                currentImages = new ArrayList<>();
                variant.setImages(currentImages);
            }

            Set<String> newImageUrls = new HashSet<>(request.getImageUrl());

            // Identify the image to delete
            List<ProductVariantImage> imagesToRemove = currentImages.stream()
                    .filter(img -> !newImageUrls.contains(img.getImageUrl()))
                    .toList();
            currentImages.removeAll(imagesToRemove);

            // Identify the image to add
            Set<String> existingUrls = currentImages.stream()
                    .map(ProductVariantImage::getImageUrl)
                    .collect(Collectors.toSet());

            List<ProductVariantImage> imagesToAdd = newImageUrls.stream()
                    .filter(url -> !existingUrls.contains(url))
                    .map(url -> {
                        ProductVariantImage img = new ProductVariantImage();
                        img.setImageUrl(url);
                        img.setVariant(variant);
                        return img;
                    }).toList();

            currentImages.addAll(imagesToAdd);
        }

        // Update new option value for variant
        List<Long> currentIds = variant.getOptionValues().stream()
                .map(optionValue -> optionValue.getOptionValue().getId())
                .toList();

        List<Long> newIds = request.getOptionValueIds() != null ? request.getOptionValueIds() : List.of();

        Set<Long> currentSet = new HashSet<>(currentIds);
        Set<Long> newSet = new HashSet<>(newIds);

        if (!currentSet.equals(newSet)) {
            List<ProductOptionValue> optionValues = productOptionValueRepository.findAllById(newIds);
            List<ProductVariantOptionValue> newOptions = optionValues.stream().map(optionValue -> {
                ProductVariantOptionValue productVariantOptionValue = new ProductVariantOptionValue();
                productVariantOptionValue.setOptionValue(optionValue);
                productVariantOptionValue.setVariant(variant);
                return productVariantOptionValue;
            }).toList();
            variant.setOptionValues(newOptions);
        }

        // Saved
        ProductVariant saved = productVariantRepository.save(variant);
        return ProductVariantMapper.toResponseDTO(saved);
    }

    @Transactional
    public void deleteVariant(Long variantId) {
        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() -> new ResourceNotFoundException("Variant not found with id " + variantId));
        productVariantRepository.delete(variant);
    }
}
