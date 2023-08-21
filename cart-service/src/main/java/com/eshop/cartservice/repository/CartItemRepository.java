package com.eshop.cartservice.repository;

import com.eshop.cartservice.models.entity.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartItemRepository extends MongoRepository<CartItem, String> {
}
