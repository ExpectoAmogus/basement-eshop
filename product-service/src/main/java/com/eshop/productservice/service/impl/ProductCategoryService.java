package com.eshop.productservice.service.impl;

import com.eshop.productservice.models.dto.ProductCategoryResponse;
import com.eshop.productservice.models.entity.ProductCategory;
import com.eshop.productservice.models.mappers.ProductCategoryMapper;
import com.eshop.productservice.repositories.ProductCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProductCategoryService {
    private final ProductCategoryRepository categoryRepository;
    private final ProductCategoryMapper<ProductCategory, ProductCategoryResponse> categoryResponseMapper;


    public ProductCategory createCategory(String categoryName, ProductCategory parent){
        ProductCategory category = ProductCategory.builder()
                .name(categoryName)
                .parent(parent)
                .build();

        log.info("Category {} is saved", category.getName());
        return categoryRepository.save(category);
    }

    public List<ProductCategoryResponse> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryResponseMapper)
                .toList();
    }
}
