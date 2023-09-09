package com.eshop.userservice.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {
    String getEmail();

    void setEmail(String email);

    void setPassword(String password);
}
