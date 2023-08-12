package com.eshop.productservice.repositories;

import com.eshop.productservice.models.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findByNameAndParentId(String name, Long parentId);
}
