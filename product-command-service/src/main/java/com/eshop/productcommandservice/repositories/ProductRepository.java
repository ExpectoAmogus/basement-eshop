package com.eshop.productcommandservice.repositories;

import com.eshop.productcommandservice.models.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByCode(String code);
}
