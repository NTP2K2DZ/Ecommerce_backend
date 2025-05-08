package com.example.backend.service;

import com.example.backend.dto.ProductCreationRequest;
import com.example.backend.dto.ProductOptionCreationRequest;
import com.example.backend.entity.Product;
import com.example.backend.entity.ProductOption;
import com.example.backend.mapper.ProductMapper;
import com.example.backend.mapper.ProductOptionMapper;
import com.example.backend.reponsitory.ProductOptionRepository;
import com.example.backend.reponsitory.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductOptionService {
    public final ProductOptionRepository productOptionRepository;
    public final ProductRepository productRepository;

    public ProductOptionService(ProductOptionRepository productOptionRepository, ProductRepository productRepository) {
        this.productOptionRepository = productOptionRepository;
        this.productRepository = productRepository;
    }

    public List<ProductOptionCreationRequest> getAllOptions() {
        return productOptionRepository.findAll().stream()
                .map(ProductOptionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductOptionCreationRequest createOption(ProductOptionCreationRequest dto) {
        // Lấy product từ DB
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id " + dto.getProductId()));

        // Map DTO -> Entity
        ProductOption option = ProductOptionMapper.toEntity(dto);
        option.setProduct(product);

        ProductOption saved = productOptionRepository.save(option);

        return ProductOptionMapper.toDTO(saved);
    }

    public List<ProductOptionCreationRequest> getOptionsByProductId(Long productId) {
        List<ProductOption> options = productOptionRepository.findByProductId(productId);
        return options.stream()
                .map(ProductOptionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteAllOptionByProductId(Long productId) {
        List<ProductOption> options = productOptionRepository.findByProductId(productId);
        productOptionRepository.deleteAll(options);
    }

    public void deleteOption(Long id) {
        productOptionRepository.deleteById(id);
    }
}
