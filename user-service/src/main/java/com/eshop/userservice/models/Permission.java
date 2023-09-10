package com.eshop.userservice.models;

import lombok.Getter;

@Getter
public enum Permission {
    FULL_ACCESS("full"),
    READ("read"),
    WRITE("write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

}
