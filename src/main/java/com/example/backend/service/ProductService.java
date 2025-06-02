package com.example.backend.service;

import com.example.backend.dto.product.ProductCreationRequest;
import com.example.backend.dto.product.ProductUpdateRequest;
import com.example.backend.dto.product.ProductRequest;
import com.example.backend.entity.Category;
import com.example.backend.entity.Product;
import com.example.backend.entity.ProductImage;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.ProductMapper;
import com.example.backend.reponsitory.CategoryRepository;
import com.example.backend.reponsitory.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductRequest> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toDTORequest)
                .collect(Collectors.toList());
    }

    public ProductRequest getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return ProductMapper.toDTORequest(product);
    }

    public List<ProductRequest> getProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return ProductMapper.toResponseList(products);
    }

    public ProductRequest createProduct(ProductCreationRequest dto) {
        Long categoryId = dto.getCategory().getId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));

        Product product = ProductMapper.toEntityCreate(dto, category);

        if (dto.getImages() != null) {
            List<ProductImage> images = dto.getImages().stream()
                    .map(url -> new ProductImage(url, product))
                    .collect(Collectors.toList());
            product.setImages(images);
        }

        Product saved = productRepository.save(product);
        return ProductMapper.toDTORequest(saved);
    }

    @Transactional
    public ProductRequest updateProduct(Long id, ProductUpdateRequest dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        if (dto.getName() != null && !dto.getName().equals(product.getName())) {
            product.setName(dto.getName());
        }

        if (dto.getDescription() != null && !dto.getDescription().equals(product.getDescription())) {
            product.setDescription(dto.getDescription());
        }

        if (dto.getPrice() != null && !dto.getPrice().equals(product.getPrice())) {
            product.setPrice(dto.getPrice());
        }

        if (dto.getQuantity() != null && !dto.getQuantity().equals(product.getQuantity())) {
            product.setQuantity(dto.getQuantity());
        }

        if (dto.getCategory() != null && dto.getCategory().getId() != null) {
            Long newCategoryId = dto.getCategory().getId();
            Long currentCategoryId = product.getCategory() != null ? product.getCategory().getId() : null;

            if (!Objects.equals(newCategoryId, currentCategoryId)) {
                Category newCategory = categoryRepository.findById(newCategoryId)
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + newCategoryId));
                product.setCategory(newCategory);
            }
        }

        if (dto.getImages() != null) {
            List<ProductImage> currentImages = product.getImages();
            if (currentImages == null) {
                currentImages = new ArrayList<>();
                product.setImages(currentImages);
            }

            Set<String> newImageUrls = new HashSet<>(dto.getImages());

            List<ProductImage> imagesToRemove = currentImages.stream()
                    .filter(img -> !newImageUrls.contains(img.getImageUrl()))
                    .toList();
            currentImages.removeAll(imagesToRemove);

            Set<String> existingUrls = currentImages.stream()
                    .map(ProductImage::getImageUrl)
                    .collect(Collectors.toSet());

            List<ProductImage> imagesToAdd = newImageUrls.stream()
                    .filter(url -> !existingUrls.contains(url))
                    .map(url -> new ProductImage(url, product))
                    .toList();

            currentImages.addAll(imagesToAdd);
        }

        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toDTORequest(updatedProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }
}
