package com.eshop.userservice.facade.impl;

import com.eshop.userservice.facade.UserFacade;
import com.eshop.userservice.service.CustomUserDetails;
import com.eshop.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userService.existsByEmail(email);
    }

    @Override
    public CustomUserDetails findByEmail(String email) {
        return userService.findByEmail(email);
    }

    @Override
    public List<CustomUserDetails> findAllByListId(List<Long> ids) {
        return userService.findAllByListId(ids);
    }

    @Override
    public List<CustomUserDetails> findAll() {
        return userService.findAll();
    }

    @Override
    public boolean isEnable(Long id, boolean enable) {
        return userService.isEnable(id, enable);
    }

    @Override
    public boolean setEnabled(Long id, boolean enable) {
        return userService.isEnable(id, enable);
    }
}
