package com.example.backend.service.impl;

import com.example.backend.dto.request.product.ProductOptionValueRequest;
import com.example.backend.dto.response.product.ProductOptionValueResponse;
import com.example.backend.entity.ProductOption;
import com.example.backend.entity.ProductOptionValue;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.ProductOptionValueMapper;
import com.example.backend.reponsitory.ProductOptionRepository;
import com.example.backend.reponsitory.ProductOptionValueRepository;
import com.example.backend.service.ProductOptionValueService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductOptionValueServiceImpl implements ProductOptionValueService {
    private final ProductOptionValueRepository productOptionValueRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductOptionValueMapper productOptionValueMapper;

    public ProductOptionValueServiceImpl(ProductOptionValueRepository productOptionValueRepository, ProductOptionRepository productOptionRepository, ProductOptionValueMapper productOptionValueMapper) {
        this.productOptionValueRepository = productOptionValueRepository;
        this.productOptionRepository = productOptionRepository;
        this.productOptionValueMapper = productOptionValueMapper;
    }

    @Override
    public List<ProductOptionValueResponse> getAllOptionValues() {
        return productOptionValueMapper.toResponseDTOList(productOptionValueRepository.findAll());
    }

    @Override
    public ProductOptionValueResponse getValueById(Long id) {
        ProductOptionValue value = productOptionValueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Value not found with id " + id));
        return productOptionValueMapper.toResponseDTO(value);
    }

    @Override
    public List<ProductOptionValueResponse> getOptionValuesByOptionId(Long optionId) {
        return productOptionValueRepository.findByOptionId(optionId).stream()
                .map(productOptionValueMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductOptionValueResponse createOptionValue(ProductOptionValueRequest request) {
        ProductOption option = productOptionRepository.findById(request.getOptionId())
                .orElseThrow(() -> new ResourceNotFoundException("Option not found with id " + request.getOptionId()));

        ProductOptionValue optionValue = new ProductOptionValue();
        optionValue.setValue(request.getValue());
        optionValue.setOption(option);

        ProductOptionValue saved = productOptionValueRepository.save(optionValue);
        return productOptionValueMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional
    public void deleteOptionValue(Long id) {
        if (!productOptionValueRepository.existsById(id)) {
            throw new ResourceNotFoundException("Option value not found with id " + id);
        }
        productOptionValueRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProductOptionValueResponse updateOptionValue(Long id, ProductOptionValueRequest request) {
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
        return productOptionValueMapper.toResponseDTO(updated);
    }
}
