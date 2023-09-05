package com.eshop.userservice.repository;

import com.eshop.userservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);

    List<Role> findAll();
}
