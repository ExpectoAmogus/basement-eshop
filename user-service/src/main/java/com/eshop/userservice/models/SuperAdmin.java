package com.eshop.userservice.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
@DiscriminatorValue("SUPERADMIN")
public class SuperAdmin extends BaseUser {

    public SuperAdmin() {
        super();
        setRole(Role.ROLE_SUPERADMIN);
    }

}
