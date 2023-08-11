package com.eshop.userservice.repository.user;

import com.eshop.userservice.models.entity.BaseUser;
import com.eshop.userservice.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseUserRepository <U extends BaseUser> extends BaseRepository<U> {

    Optional<BaseUser> findByEmail(String email);

    boolean existsByEmail(String email);
}
