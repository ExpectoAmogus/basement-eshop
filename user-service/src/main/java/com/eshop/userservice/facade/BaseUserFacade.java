package com.eshop.userservice.facade;

import com.eshop.userservice.models.BaseUser;

public interface BaseUserFacade {
    BaseUser findByEmail(String email);
}
