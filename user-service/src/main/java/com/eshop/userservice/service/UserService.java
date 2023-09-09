package com.eshop.userservice.service;

import java.util.List;

public interface UserService extends BaseUserService<CustomUserDetails> {

    boolean existsByEmail(String email);

    List<CustomUserDetails> findAllByListId(List<Long> ids);

    List<CustomUserDetails> findAll();

    boolean isEnable(Long id, boolean enable); // метод бана

}
