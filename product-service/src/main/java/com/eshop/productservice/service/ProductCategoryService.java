package com.eshop.productservice.service;

import com.eshop.productservice.models.dto.ProductCategoryDto;
import com.eshop.productservice.models.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory createCategory(ProductCategory productCategory);
    List<ProductCategory> getAllCategories();
    ProductCategory findById(Long id);
    ProductCategory findByNameAndParentId(String name, Long parentId);
}
