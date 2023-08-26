package com.eshop.productservice.service.impl;

import com.eshop.productservice.models.entity.ProductCategory;
import com.eshop.productservice.repositories.ProductCategoryRepository;
import com.eshop.productservice.service.ProductCategoryService;
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
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository categoryRepository;

    @Override
    public ProductCategory createCategory(ProductCategory productCategory) {
        Long parentId = null;
        if (productCategory.getParentId() != null) {
            ProductCategory parentCategory = categoryRepository.findById(productCategory.getParentId()).orElse(null);
            if (parentCategory != null) {
                parentId = parentCategory.getId();
            }

        }
        ProductCategory category = ProductCategory.builder()
                .name(productCategory.getName())
                .parentId(parentId)
                .build();

        ProductCategory savedCategory = categoryRepository.save(category);
        log.info("Category {} is saved", savedCategory.getName());
        return savedCategory;
    }

    @Override
    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public ProductCategory findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ProductCategory findByNameAndParentId(String name, Long parentId) {
        return categoryRepository.findByNameAndParentId(name, parentId)
                .orElseThrow(EntityNotFoundException::new);
    }
}
