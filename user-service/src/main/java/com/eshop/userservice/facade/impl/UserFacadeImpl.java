package com.eshop.userservice.facade.impl;

import com.eshop.userservice.facade.UserFacade;
import com.eshop.userservice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean setEnabled(Long id, boolean enable) {
        return userService.isEnable(id,enable);
    }
}
