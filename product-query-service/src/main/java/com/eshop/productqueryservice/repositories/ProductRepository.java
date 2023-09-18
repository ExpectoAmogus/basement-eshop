package com.eshop.productqueryservice.repositories;

import com.eshop.productqueryservice.models.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByCode(String code);
}
