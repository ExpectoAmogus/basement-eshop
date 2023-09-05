package com.eshop.userservice.facade;

import com.eshop.userservice.models.Role;
import com.eshop.userservice.models.User;

import java.util.List;

public interface UserFacade {
    boolean existsByEmail(String email);

    User findByEmail(String email);

    List<User> findAllByListId(List<Long> ids);

    List<User> findAll();

    boolean isEnable(Long id, boolean enable); // метод бана

    Role saveRole(Role role);

//    void addRoleToUser(String roleName, String email);

    boolean setEnabled(Long id, boolean enable);
}
