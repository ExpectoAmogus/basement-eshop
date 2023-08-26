package com.eshop.cartservice.facade.impl;

import com.eshop.cartservice.facade.CartFacade;
import com.eshop.cartservice.models.dto.CartDto;
import com.eshop.cartservice.models.entity.Cart;
import com.eshop.cartservice.models.mappers.CartDtoMapper;
import com.eshop.cartservice.service.CartService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartFacadeImpl implements CartFacade {
    private final CartService cartService;
    private final CartDtoMapper cartDtoMapper;

    @Override
    public void addItemToCart(String userId, String productId, int quantity) {
        cartService.addItemToCart(userId, productId, quantity);
    }

    @Override
    public void removeItemFromCart(String userId, String itemId) {
        try {
            cartService.removeItemFromCart(userId, itemId);
        }
        catch (NotFoundException e){
            log.error("Cart not found!");
        }
    }

    @Override
    public void updateCartItemQuantity(String userId, String itemId, int newQuantity) {
        try {
            cartService.updateCartItemQuantity(userId, itemId, newQuantity);
        }
        catch (NotFoundException e){
            log.error("Cart not found!");
        }

    }

    @Override
    public CartDto getCartByUserId(String userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return cartDtoMapper.apply(cart);
    }
}
