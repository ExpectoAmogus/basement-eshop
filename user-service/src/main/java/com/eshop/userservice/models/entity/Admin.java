package com.eshop.userservice.models.entity;

import com.eshop.userservice.models.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends BaseUser {

    public Admin() {
        super();
        setRole(Role.ROLE_ADMIN);
    }

}
