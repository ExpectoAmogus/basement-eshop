package com.eshop.userservice.repository.user;

import com.eshop.userservice.models.BaseUser;
import com.eshop.userservice.repository.BaseRepository;
import com.eshop.userservice.service.CustomUserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseUserRepository<U extends BaseUser> extends BaseRepository<U> {

    Optional<CustomUserDetails> findByEmail(String email);

    boolean existsByEmail(String email);
}
