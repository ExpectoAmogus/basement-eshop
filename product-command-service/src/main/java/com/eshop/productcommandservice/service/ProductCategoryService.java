package com.eshop.productcommandservice.service;

import com.eshop.productcommandservice.models.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory createCategory(ProductCategory productCategory);
    void deleteCategory(ProductCategory productCategory);
    List<ProductCategory> getAllCategories();
    List<ProductCategory> findAllByParentId(String id);
    ProductCategory findById(String id);
    ProductCategory findByNameAndParentId(String name, String parentId);

}
