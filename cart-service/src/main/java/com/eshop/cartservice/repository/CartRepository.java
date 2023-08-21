package com.eshop.cartservice.repository;

import com.eshop.cartservice.models.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {
}
