package com.eshop.productservice.repositories;

import com.eshop.productservice.models.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
