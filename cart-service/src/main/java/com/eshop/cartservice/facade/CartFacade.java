package com.eshop.cartservice.facade;

import com.eshop.cartservice.models.dto.CartDto;

public interface CartFacade {
    void addItemToCart(String userId, String productId, int quantity);
    void removeItemFromCart(String userId, String itemId);
    void updateCartItemQuantity(String userId, String itemId, int newQuantity);
    CartDto getCartByUserId(String userId);
}
