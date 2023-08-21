package com.eshop.cartservice.service;

import com.eshop.cartservice.models.entity.Cart;

public interface CartService {
    void addItemToCart(String userId, String productId, int quantity);
    void removeItemFromCart(String userId, String itemId);
    void updateCartItemQuantity(String userId, String itemId, int newQuantity);
    Cart getCartByUserId(String userId);
}
