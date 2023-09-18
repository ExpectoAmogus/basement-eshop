package com.eshop.productqueryservice.repositories;

import com.eshop.productqueryservice.models.entity.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository extends MongoRepository<ProductCategory, String> {
    Optional<ProductCategory> findByNameAndParentId(String name, String parentId);
    Optional<List<ProductCategory>> findAllByParentId(String parentId);
}
