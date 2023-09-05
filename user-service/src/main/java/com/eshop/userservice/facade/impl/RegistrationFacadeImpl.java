package com.eshop.userservice.facade.impl;

import com.eshop.userservice.dto.AuthDto;
import com.eshop.userservice.facade.RegistrationFacade;
import com.eshop.userservice.models.User;
import com.eshop.userservice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final UserService userService;

    public RegistrationFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void registration(AuthDto authDtO) {
        User user = new User();
        user.setEmail(authDtO.getEmail());
        user.setPassword(authDtO.getPassword());
        userService.create(user);
    }
}
