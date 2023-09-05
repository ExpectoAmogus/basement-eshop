package com.eshop.userservice.service;

import com.eshop.userservice.models.BaseUser;

public interface BaseUserService<U extends BaseUser> extends CrudService<U> { }
