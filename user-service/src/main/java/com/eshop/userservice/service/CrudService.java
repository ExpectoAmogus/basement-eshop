package com.eshop.userservice.service;

public interface CrudService<AE extends CustomUserDetails> {

    void create(AE ae);

    void update(AE ae);

    void delete(Long id);

    AE findById(Long id);
}
