package com.eshop.productservice.repositories;

import com.eshop.productservice.models.entity.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends MongoRepository<ProductCategory, String> {
    Optional<ProductCategory> findByNameAndParentId(String name, String parentId);
}
