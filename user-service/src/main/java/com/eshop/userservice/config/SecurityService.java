package com.eshop.userservice.config;

public interface SecurityService {

    boolean isAuthenticated();

    void autoLogin(String username, String password);
}
