package com.eshop.userservice.service;

import com.eshop.userservice.models.entity.User;

import java.util.List;

public interface UserService extends  BaseUserService<User>{

    boolean existsByEmail(String email);

    User findByEmail(String email);

    List<User> findAllByListId(List<Long> ids);

    List<User> findAll();

    boolean isEnable(Long id, boolean enable); // метод бана
}
