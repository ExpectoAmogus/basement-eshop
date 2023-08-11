package com.eshop.userservice.service;

import com.eshop.userservice.models.entity.BaseUser;

public interface CrudService <AE extends BaseUser> {

    void create(AE ae);
    void update(AE ae);
    void delete(Long id);
    AE findById(Long id);
}
