package com.example.backend.service.impl;

import com.example.backend.dto.request.product.ProductOptionRequest;
import com.example.backend.dto.response.product.ProductOptionResponse;
import com.example.backend.entity.Product;
import com.example.backend.entity.ProductOption;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.ProductOptionMapper;
import com.example.backend.reponsitory.ProductOptionRepository;
import com.example.backend.reponsitory.ProductRepository;
import com.example.backend.service.ProductOptionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOptionServiceImpl implements ProductOptionService {

    private final ProductOptionRepository productOptionRepository;
    private final ProductRepository productRepository;
    private final ProductOptionMapper productOptionMapper;


    public ProductOptionServiceImpl(ProductOptionRepository productOptionRepository, ProductRepository productRepository, ProductOptionMapper productOptionMapper) {
        this.productOptionRepository = productOptionRepository;
        this.productRepository = productRepository;
        this.productOptionMapper = productOptionMapper;
    }

    @Override
    public List<ProductOptionResponse> getAllOptions() {
        return productOptionMapper.toResponseDTOList(productOptionRepository.findAll());
    }



    @Override
    public ProductOptionResponse getOptionById(Long id) {
        ProductOption option = productOptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Option not found with id " + id));
        return productOptionMapper.toResponseDTO(option);
    }

    @Override
    @Transactional
    public ProductOptionResponse createOption(ProductOptionRequest request) {
        Long productId = request.getProductId();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));

        ProductOption option = new ProductOption();
        option.setName(request.getName());
        option.setProduct(product);
        ProductOption saved = productOptionRepository.save(option);
        return productOptionMapper.toResponseDTO(saved);
    }

    @Override
    public ProductOptionResponse updateOption(Long id, ProductOptionRequest request) {
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
        return productOptionMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteOption(Long id) {
        if (!productOptionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Option not found with id " + id);
        }
        productOptionRepository.deleteById(id);
    }
}
