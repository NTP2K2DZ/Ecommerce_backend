package com.example.backend.service;

import com.example.backend.dto.product.ProductCreationRequest;
import com.example.backend.dto.product.ProductUpdateRequest;
import com.example.backend.dto.product.ProductResponse;
import com.example.backend.entity.Brand;
import com.example.backend.entity.Category;
import com.example.backend.entity.Product;
import com.example.backend.entity.ProductImage;
import com.example.backend.exception.BadRequestException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.ProductMapper;
import com.example.backend.reponsitory.BrandRepository;
import com.example.backend.reponsitory.CategoryRepository;
import com.example.backend.reponsitory.ProductRepository;
import com.example.backend.util.SlugUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
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

    public List<ProductResponse> getProductsByBrandId(Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + brandId));
        List<Product> products = productRepository.findByBrandId(brandId);
        return ProductMapper.toResponseList(products);
    }

    @Transactional
    public ProductResponse createProduct(ProductCreationRequest request) {
        Long categoryId = request.getCategoryId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));

        Long brandId = request.getBrandId();
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + brandId));

        String slug = SlugUtil.toProductSlug(request.getName());
        if (productRepository.existsBySlug(slug)) {
            throw new BadRequestException("Slug already exists: " + slug);
        }

        Product product = ProductMapper.toEntityCreate(request, category, brand);
        product.setSlug(slug);

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
            String newSlug = SlugUtil.toProductSlug(request.getName());
            if (productRepository.existsBySlug(newSlug) && !Objects.equals(product.getSlug(), newSlug)) {
                throw new BadRequestException("Slug already exists: " + newSlug);
            }
            product.setName(request.getName());
            product.setSlug(newSlug);
        }

        if (request.getDescription() != null && !request.getDescription().equals(product.getDescription())) {
            product.setDescription(request.getDescription());
        }

        if (request.getPrice() != null && !request.getPrice().equals(product.getPrice())) {
            product.setPrice(request.getPrice());
        }

        if (request.getCategoryId() != null) {
            Long newCategoryId = request.getCategoryId();
            Long currentCategoryId = product.getCategory() != null ? product.getCategory().getId() : null;

            if (!Objects.equals(newCategoryId, currentCategoryId)) {
                Category newCategory = categoryRepository.findById(newCategoryId)
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + newCategoryId));
                product.setCategory(newCategory);
            }
        }

        if (request.getBrandId() != null) {
            Long newBrandId = request.getBrandId();
            Long currentBrandId = product.getBrand() != null ? product.getBrand().getId() : null;

            if (!Objects.equals(newBrandId, currentBrandId)) {
                Brand newBrand = brandRepository.findById(newBrandId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + newBrandId));
                product.setBrand(newBrand);
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
