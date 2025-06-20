package com.example.backend.service;

import com.example.backend.dto.product_option_value.ProductOptionValueCreationRequest;
import com.example.backend.dto.product_option_value.ProductOptionValueResponse;
import com.example.backend.dto.product_option_value.ProductOptionValueUpdateRequest;
import com.example.backend.entity.ProductOption;
import com.example.backend.entity.ProductOptionValue;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.ProductOptionValueMapper;
import com.example.backend.reponsitory.ProductOptionRepository;
import com.example.backend.reponsitory.ProductOptionValueRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductOptionValueService {
    public final ProductOptionValueRepository productOptionValueRepository;
    public final ProductOptionRepository productOptionRepository;

    public ProductOptionValueService(ProductOptionValueRepository productOptionValueRepository, ProductOptionRepository productOptionRepository) {
        this.productOptionValueRepository = productOptionValueRepository;
        this.productOptionRepository = productOptionRepository;
    }

    public List<ProductOptionValueResponse> getAllOptionValues() {
        return productOptionValueRepository.findAll().stream()
                .map(ProductOptionValueMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ProductOptionValueResponse getValueById (Long id) {
        ProductOptionValue value = productOptionValueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Value not found with id " + id));
        return ProductOptionValueMapper.toResponseDTO(value);
    }


    public List<ProductOptionValueResponse> getOptionValuesByOptionId(Long optionId) {
        return productOptionValueRepository.findByOptionId(optionId).stream()
                .map(ProductOptionValueMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


    public ProductOptionValueResponse createOptionValue(ProductOptionValueCreationRequest request) {
        Long optionId = request.getOptionId();

        ProductOption option = productOptionRepository.findById(optionId)
                .orElseThrow(() -> new ResourceNotFoundException("Option not found with id " + optionId));

        ProductOptionValue optionValue = ProductOptionValueMapper.toEntityCreate(request, option);

        ProductOptionValue saved = productOptionValueRepository.save(optionValue);

        return ProductOptionValueMapper.toResponseDTO(saved);
    }


    public void deleteOptionValue(Long id) {
        if (!productOptionValueRepository.existsById(id)) {
            throw new ResourceNotFoundException("Option value not found with id " + id);
        }
        productOptionValueRepository.deleteById(id);
    }

    @Transactional
    public ProductOptionValueResponse updateOptionValue(Long id, ProductOptionValueUpdateRequest request) {
        ProductOptionValue optionValue = productOptionValueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product option value not found with id " + id));

        if (request.getValue() != null && !Objects.equals(request.getValue(), optionValue.getValue())) {
            optionValue.setValue(request.getValue());
        }

        if (request.getOptionId() != null && !Objects.equals(request.getOptionId(), optionValue.getProductOption().getId())) {
            ProductOption option = productOptionRepository.findById(request.getOptionId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product option not found with id " + request.getOptionId()));
            optionValue.setProductOption(option);
        }

        ProductOptionValue updated = productOptionValueRepository.save(optionValue);
        return ProductOptionValueMapper.toResponseDTO(updated);
    }

}
