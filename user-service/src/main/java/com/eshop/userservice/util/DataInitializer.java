package com.eshop.userservice.util;

import com.eshop.userservice.models.Admin;
import com.eshop.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserService userService;

    public void initAdmin() {
        if (!userService.existsByEmail("admin@gmail.com")) {
            Admin admin = new Admin();
            admin.setEmail("admin@gmail.com");
            admin.setPassword("admin");
            userService.create(admin);
        }
    }
}