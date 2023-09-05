package com.eshop.userservice.facade.impl;

import com.eshop.userservice.facade.UserFacade;
import com.eshop.userservice.models.BaseUser;
import com.eshop.userservice.models.Role;
import com.eshop.userservice.models.User;
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
    public BaseUser findByEmail(String email) {
        return userService.findByEmail(email);
    }

    @Override
    public List<User> findAllByListId(List<Long> ids) {
        return userService.findAllByListId(ids);
    }

    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

    @Override
    public boolean isEnable(Long id, boolean enable) {
        return userService.isEnable(id,enable);
    }

//    @Override
//    public Role saveRole(Role role) {
//        return userService.saveRole(role);
//    }

//    @Override
//    public void addRoleToUser(String roleName, String email) {
//        userService.addRoleToUser(roleName,email);
//    }

    @Override
    public boolean setEnabled(Long id, boolean enable) {
        return userService.isEnable(id,enable);
    }
}
