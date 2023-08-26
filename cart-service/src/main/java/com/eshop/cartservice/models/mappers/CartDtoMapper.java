package com.eshop.cartservice.models.mappers;

import com.eshop.cartservice.models.dto.CartDto;
import com.eshop.cartservice.models.dto.CartItemDto;
import com.eshop.cartservice.models.entity.Cart;
import com.eshop.cartservice.models.entity.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class CartDtoMapper implements Function<Cart, CartDto> {
    @Override
    public CartDto apply(Cart cart) {
        List<CartItemDto> itemDtos = cart.getItems().stream()
                .map(this::mapCartItemToDto)
                .toList();

        return new CartDto(
                cart.getUserId(),
                itemDtos
        );
    }

    private CartItemDto mapCartItemToDto(CartItem cartItem) {
        return new CartItemDto(cartItem.getProductId(), cartItem.getQuantity());
    }
}
