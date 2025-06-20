package com.example.backend.service;

import com.example.backend.dto.product_option.ProductOptionCreationRequest;
import com.example.backend.dto.product_option.ProductOptionResponse;
import com.example.backend.dto.product_option.ProductOptionUpdateRequest;
import com.example.backend.entity.Product;
import com.example.backend.entity.ProductOption;
import com.example.backend.exception.ResourceNotFoundException;
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

    public List<ProductOptionResponse> getAllOptions() {
        return productOptionRepository.findAll().stream()
                .map(ProductOptionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ProductOptionResponse getOptionById(Long id) {
        ProductOption option = productOptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Option not found with id " + id));
        return ProductOptionMapper.toResponseDTO(option);
    }

    public ProductOptionResponse createOption(ProductOptionCreationRequest request) {
        Long productId = request.getProductId();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));

        ProductOption option = ProductOptionMapper.toEntityCreate(request, product);
        ProductOption saved = productOptionRepository.save(option);

        return ProductOptionMapper.toResponseDTO(saved);
    }

    public ProductOptionResponse updateOption(Long id, ProductOptionUpdateRequest request) {
        ProductOption option = productOptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Option not found with id " + id));

        if (request.getName() != null && !request.getName().equals(option.getName())) {
            option.setName(request.getName());
        }

        if (request.getProductId() != null && !request.getProductId().equals(option.getProduct().getId())) {
            Product newProduct = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + request.getProductId()));
            option.setProduct(newProduct);
        }

        ProductOption updated = productOptionRepository.save(option);
        return ProductOptionMapper.toResponseDTO(updated);
    }

    public void deleteOption(Long id) {
        if (!productOptionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Option not found with id " + id);
        }
        productOptionRepository.deleteById(id);
    }
}
