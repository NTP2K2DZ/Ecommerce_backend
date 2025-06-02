//package com.example.backend.service;
//
//import com.example.backend.dto.product_image.ProductImageCreationRequest;
//import com.example.backend.dto.product_image.ProductImageRequest;
//import com.example.backend.entity.Product;
//import com.example.backend.entity.ProductImage;
//import com.example.backend.reponsitory.ProductImageRepository;
//import com.example.backend.reponsitory.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ProductImageService {
//    @Autowired
//    private ProductImageRepository productImageRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
////    public ProductImageCreationRequest upLoadImage(Long productId, String imageUrl) {
////        Product product = productRepository.findById(productId)
////                .orElseThrow(() ->new RuntimeException("Product not found with id" + productId));
////        ProductImage image = new ProductImage(imageUrl, product);
////        ProductImage saved = productImageRepository.save(image);
////        return new ProductImageRequest((saved.getId()), saved.getImageUrl());
////    }
//
////    public void deleteImage(Long imageId) {
////        if (!productImageRepository.existsById(imageId)) {
////            throw new RuntimeException("Image not found with id " + imageId);
////        }
////        productImageRepository.deleteById(imageId);
////    }
////
////    public ProductImageCreationRequest updateImageUrl(Long imageId, String newUrl) {
////
////        ProductImage image = productImageRepository.findById(imageId)
////                .orElseThrow(() -> new RuntimeException("Image not found with id " + imageId));
////
////        image.setImageUrl(newUrl);
////        ProductImage updated = productImageRepository.save(image);
////
////        return new ProductImageCreationRequest(updated.getId(), updated.getImageUrl());
////    }
//
////    public List<ProductImageCreationRequest> getImagesByProductId (Long productId) {
////        return productImageRepository.findByProductId(productId).stream()
////                .map(img -> new ProductImageCreationRequest(img.getId(), img.getImageUrl()))
////                .collect(Collectors.toList());
////    }
//
//    public void deleteAllImagesByProductId(Long productId) {
//        List<ProductImage> images = productImageRepository.findByProductId(productId);
//        if (images.isEmpty()) {
//            return;
//        }
//        productImageRepository.deleteAll(images);
//    }
//}
