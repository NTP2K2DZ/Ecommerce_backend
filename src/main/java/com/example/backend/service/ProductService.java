package com.example.backend.service;

import com.example.backend.dto.product.ProductCreationRequest;
import com.example.backend.dto.product.ProductUpdateRequest;
import com.example.backend.dto.product.ProductResponse;
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

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return ProductMapper.toResponseDTO(product);
    }

    public List<ProductResponse> getProductsByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found with id " + categoryId));

        List<Product> products = productRepository.findByCategoryId(categoryId);
        return ProductMapper.toResponseList(products);
    }

    public ProductResponse createProduct(ProductCreationRequest request) {
        Long categoryId = request.getCategory().getId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));

        Product product = ProductMapper.toEntityCreate(request, category);

        if (request.getImages() != null) {
            List<ProductImage> images = request.getImages().stream()
                    .map(url -> new ProductImage(url, product))
                    .collect(Collectors.toList());
            product.setImages(images);
        }

        Product saved = productRepository.save(product);
        return ProductMapper.toResponseDTO(saved);
    }

    @Transactional
    public ProductResponse updateProduct(Long id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        if (request.getName() != null && !request.getName().equals(product.getName())) {
            product.setName(request.getName());
        }

        if (request.getDescription() != null && !request.getDescription().equals(product.getDescription())) {
            product.setDescription(request.getDescription());
        }

        if (request.getPrice() != null && !request.getPrice().equals(product.getPrice())) {
            product.setPrice(request.getPrice());
        }

        if (request.getQuantity() != null && !request.getQuantity().equals(product.getQuantity())) {
            product.setQuantity(request.getQuantity());
        }

        if (request.getCategory() != null && request.getCategory().getId() != null) {
            Long newCategoryId = request.getCategory().getId();
            Long currentCategoryId = product.getCategory() != null ? product.getCategory().getId() : null;

            if (!Objects.equals(newCategoryId, currentCategoryId)) {
                Category newCategory = categoryRepository.findById(newCategoryId)
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + newCategoryId));
                product.setCategory(newCategory);
            }
        }

        if (request.getImages() != null) {
            List<ProductImage> currentImages = product.getImages();
            if (currentImages == null) {
                currentImages = new ArrayList<>();
                product.setImages(currentImages);
            }

            Set<String> newImageUrls = new HashSet<>(request.getImages());

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
        return ProductMapper.toResponseDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }
}
