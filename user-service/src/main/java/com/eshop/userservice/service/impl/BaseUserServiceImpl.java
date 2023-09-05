package com.eshop.userservice.service.impl;

import com.eshop.userservice.models.BaseUser;
import com.eshop.userservice.repository.user.UserRepository;
import com.eshop.userservice.service.BaseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseUserServiceImpl implements BaseUserService<BaseUser> {
    private final UserRepository userRepository;
    @Override
    public BaseUser findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User by this email not found"));
    }

    @Override
    public void create(BaseUser baseUser) {

    }

    @Override
    public void update(BaseUser baseUser) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public BaseUser findById(Long id) {
        return null;
    }
}
