package com.eshop.cartservice.controller;

import com.eshop.cartservice.facade.CartFacade;
import com.eshop.cartservice.models.dto.CartDto;
import com.eshop.cartservice.models.entity.Cart;
import com.eshop.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/cart")
public class CartController {
    private final CartFacade cartFacade;

    @PostMapping("/{userId}/addItem")
    public ResponseEntity<String> addItemToCart(
            @PathVariable String userId,
            @RequestParam String productId,
            @RequestParam int quantity) {
        cartFacade.addItemToCart(userId, productId, quantity);
        return ResponseEntity.ok("Item added to cart successfully");
    }

    @DeleteMapping("/{userId}/removeItem/{itemId}")
    public ResponseEntity<String> removeItemFromCart(
            @PathVariable String userId,
            @PathVariable String itemId) {
        cartFacade.removeItemFromCart(userId, itemId);
        return ResponseEntity.ok("Item removed from cart successfully");
    }

    @PutMapping("/{userId}/updateQuantity/{itemId}")
    public ResponseEntity<String> updateCartItemQuantity(
            @PathVariable String userId,
            @PathVariable String itemId,
            @RequestParam int newQuantity) {
        cartFacade.updateCartItemQuantity(userId, itemId, newQuantity);
        return ResponseEntity.ok("Item quantity updated successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable String userId) {
        CartDto cart = cartFacade.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }
}
