package com.eshop.productservice.repositories;

import com.eshop.productservice.models.entity.ProductSpec;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductSpecRepository extends MongoRepository<ProductSpec, String> {
}
