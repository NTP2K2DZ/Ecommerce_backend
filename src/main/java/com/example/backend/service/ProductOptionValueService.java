package com.example.backend.service;

import com.example.backend.dto.ProductOptionValueCreationRequest;
import com.example.backend.entity.ProductOption;
import com.example.backend.entity.ProductOptionValue;
import com.example.backend.mapper.ProductOptionValueMapper;
import com.example.backend.reponsitory.ProductOptionRepository;
import com.example.backend.reponsitory.ProductOptionValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductOptionValueService {
    public final ProductOptionValueRepository productOptionValueRepository;
    public final ProductOptionRepository productOptionRepository;

    public ProductOptionValueService(ProductOptionValueRepository productOptionValueRepository, ProductOptionRepository productOptionRepository) {
        this.productOptionValueRepository = productOptionValueRepository;
        this.productOptionRepository = productOptionRepository;
    }

    public List<ProductOptionValueCreationRequest> getAllOptionValues() {
        return productOptionValueRepository.findAll().stream()
                .map(ProductOptionValueMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProductOptionValueCreationRequest> getOptionValuesByOptionId(Long optionId) {
        return productOptionValueRepository.findByProductOptionId(optionId).stream()
                .map(ProductOptionValueMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductOptionValueCreationRequest createOptionValue(ProductOptionValueCreationRequest dto) {
        ProductOption productOption = productOptionRepository.findById(dto.getOptionId())
                .orElseThrow(() -> new RuntimeException("Product option not found with id" + dto.getOptionId()));

        ProductOptionValue optionValue = ProductOptionValueMapper.toEntity(dto);
        optionValue.setProductOption(productOption);

        ProductOptionValue saved = productOptionValueRepository.save(optionValue);

        return ProductOptionValueMapper.toDTO(saved);
    }

    public void deleteOptionValue(Long id) {
        if (!productOptionValueRepository.existsById(id)) {
            throw new RuntimeException("OptionValue with ID " + id + " not found");
        }
        productOptionValueRepository.deleteById(id);
    }

    public ProductOptionValueCreationRequest updateOptionValue(Long id, ProductOptionValueCreationRequest dto) {
        ProductOptionValue existing = productOptionValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Option value not found with ID: " + id));

        if (dto.getValue() != null && !dto.getValue().equals(existing.getValue())) {
            existing.setValue(dto.getValue());
        }

        if (dto.getOptionId() != null &&
                !existing.getProductOption().getId().equals(dto.getOptionId())) {
            // Search new option
            ProductOption newOption = productOptionRepository.findById(dto.getOptionId())
                    .orElseThrow(() -> new RuntimeException("Product option not found with ID: " + dto.getOptionId()));
            // Assign new product option to current option value
            existing.setProductOption(newOption);
        }

        ProductOptionValue updated = productOptionValueRepository.save(existing);

        return ProductOptionValueMapper.toDTO(updated);
    }

}
