package com.eshop.userservice.models;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum Role {
    ROLE_ADMIN(Set.of(Permission.READ, Permission.WRITE)),
    ROLE_USER(Set.of(Permission.READ)),
    ROLE_SUPERADMIN(Set.of(Permission.FULL_ACCESS));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
