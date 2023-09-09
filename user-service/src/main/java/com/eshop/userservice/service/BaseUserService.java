package com.eshop.userservice.service;

public interface BaseUserService<U extends CustomUserDetails> extends CrudService<U> {
    CustomUserDetails findByEmail(String email);
}
