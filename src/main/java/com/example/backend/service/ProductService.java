package com.example.backend.service;

import com.example.backend.dto.CategoryCreationRequest;
import com.example.backend.dto.ProductCreationRequest;
import com.example.backend.dto.ProductImageCreationRequest;
import com.example.backend.entity.Category;
import com.example.backend.entity.Product;
import com.example.backend.entity.ProductImage;
import com.example.backend.mapper.ProductMapper;
import com.example.backend.reponsitory.CategoryRepository;
import com.example.backend.reponsitory.ProductImageRepository;
import com.example.backend.reponsitory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageService productImageService;
    private final ProductOptionService productOptionService;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductImageService productImageService, ProductOptionService productOptionService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productImageService = productImageService;
        this.productOptionService = productOptionService;
    }

    public List<ProductCreationRequest> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductCreationRequest getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        return ProductMapper.toDTO(product);
    }

    public ProductCreationRequest createProduct(ProductCreationRequest dto) {
        Long categoryId = dto.getCategory().getId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + categoryId));

        Product product = ProductMapper.toEntity(dto, category);

        if (dto.getImages() != null) {
            List<ProductImage> images = dto.getImages().stream()
                    .map(imgDto -> new ProductImage(imgDto.getImageUrl(), product))
                    .collect(Collectors.toList());
            product.setImages(images);
        }

        Product saved = productRepository.save(product);
        return ProductMapper.toDTO(saved);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }
}
