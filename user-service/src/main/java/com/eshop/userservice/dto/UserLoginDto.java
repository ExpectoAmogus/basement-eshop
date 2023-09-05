package com.eshop.userservice.dto;

public record UserLoginDto(
        String email,
        String password
) {
}
