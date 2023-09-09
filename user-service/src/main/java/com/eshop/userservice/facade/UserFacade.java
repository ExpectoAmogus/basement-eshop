package com.eshop.userservice.facade;

import com.eshop.userservice.service.CustomUserDetails;

import java.util.List;

public interface UserFacade extends BaseUserFacade {
    boolean existsByEmail(String email);

    List<CustomUserDetails> findAllByListId(List<Long> ids);

    List<CustomUserDetails> findAll();

    boolean isEnable(Long id, boolean enable); // метод бана

    boolean setEnabled(Long id, boolean enable);
}
