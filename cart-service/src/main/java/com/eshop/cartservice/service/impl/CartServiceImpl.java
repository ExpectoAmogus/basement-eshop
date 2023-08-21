package com.eshop.cartservice.service.impl;

import com.eshop.cartservice.models.entity.Cart;
import com.eshop.cartservice.models.entity.CartItem;
import com.eshop.cartservice.repository.CartItemRepository;
import com.eshop.cartservice.repository.CartRepository;
import com.eshop.cartservice.service.CartService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public void addItemToCart(String userId, String productId, int quantity) {
        Cart cart = cartRepository.findById(userId).orElse(new Cart(userId, new ArrayList<>()));
        Optional<CartItem> existingCartItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            existingCartItem.get().setQuantity(existingCartItem.get().getQuantity() + quantity);
        } else {
            CartItem newCartItem = CartItem.builder()
                    .productId(productId)
                    .quantity(quantity)
                    .build();
            cart.getItems().add(newCartItem);
        }

        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(String userId, String itemId) {
        Cart cart = cartRepository.findById(userId).orElseThrow(() -> new NotFoundException("Cart not found"));

        cart.getItems().removeIf(item -> item.getId().equals(itemId));

        cartRepository.save(cart);
    }

    @Override
    public void updateCartItemQuantity(String userId, String itemId, int newQuantity) {
        Cart cart = cartRepository.findById(userId).orElseThrow(() -> new NotFoundException("Cart not found"));

        cart.getItems().forEach(item -> {
            if (item.getId().equals(itemId)) {
                item.setQuantity(newQuantity);
            }
        });

        cartRepository.save(cart);
    }

    @Override
    public Cart getCartByUserId(String userId) {
        return cartRepository.findById(userId).orElse(new Cart(userId, new ArrayList<>()));
    }
}
