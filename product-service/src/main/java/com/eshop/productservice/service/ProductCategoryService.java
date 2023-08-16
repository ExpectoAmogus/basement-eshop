package com.eshop.productservice.service;

import com.eshop.productservice.models.dto.ProductCategoryDto;

import java.util.List;

public interface ProductCategoryService {
    ProductCategoryDto createCategory(ProductCategoryDto productCategoryDto);
    List<ProductCategoryDto> getAllCategories();
    ProductCategoryDto findById(Long id);
    ProductCategoryDto findByNameAndParentId(String name, Long parentId);
}
