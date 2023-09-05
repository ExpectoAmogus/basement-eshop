package com.eshop.userservice.dto;

public record UserRegistrationDto(
        String email,
        String password,
        String passwordConfirm
) {
}
