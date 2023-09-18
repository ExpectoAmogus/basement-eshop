package com.eshop.productqueryservice.service;

import com.eshop.productqueryservice.models.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getAllCategories();
    ProductCategory findById(String id);
    ProductCategory findByNameAndParentId(String name, String parentId);
    List<ProductCategory> findAllByParentId(String parentId);
}
