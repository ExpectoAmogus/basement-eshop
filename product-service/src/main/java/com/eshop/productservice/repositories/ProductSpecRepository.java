package com.eshop.productservice.repositories;

import com.eshop.productservice.models.entity.ProductSpec;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSpecRepository extends JpaRepository<ProductSpec, Long> {
}
