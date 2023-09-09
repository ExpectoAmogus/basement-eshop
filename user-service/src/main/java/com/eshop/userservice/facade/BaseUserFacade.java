package com.eshop.userservice.facade;

import com.eshop.userservice.service.CustomUserDetails;

public interface BaseUserFacade {
    CustomUserDetails findByEmail(String email);
}
