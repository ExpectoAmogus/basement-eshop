package com.eshop.productservice.service.impl;

import com.eshop.productservice.models.dto.ProductCategoryDto;
import com.eshop.productservice.models.entity.ProductCategory;
import com.eshop.productservice.models.mappers.ProductCategoryDtoMapper;
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
    private final ProductCategoryDtoMapper categoryDtoMapper;

    @Override
    public ProductCategoryDto createCategory(ProductCategoryDto productCategoryDto) {
        Long parentId = null;
        if (productCategoryDto.parentId() != null) {
            ProductCategory parentCategory = categoryRepository.findById(productCategoryDto.parentId()).orElse(null);
            if (parentCategory != null) {
                parentId = parentCategory.getId();
            }

        }
        ProductCategory category = ProductCategory.builder()
                .name(productCategoryDto.name())
                .parentId(parentId)
                .build();

        ProductCategory savedCategory = categoryRepository.save(category);
        log.info("Category {} is saved", savedCategory.getName());
        return categoryDtoMapper.apply(savedCategory);
    }

    @Override
    public List<ProductCategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryDtoMapper)
                .toList();
    }

    @Override
    public ProductCategoryDto findById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryDtoMapper)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ProductCategoryDto findByNameAndParentId(String name, Long parentId) {
        return categoryRepository.findByNameAndParentId(name, parentId)
                .map(categoryDtoMapper)
                .orElse(null);
    }
}
