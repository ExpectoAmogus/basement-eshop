package com.eshop.userservice.service.impl;

import com.eshop.userservice.models.entity.User;
import com.eshop.userservice.repository.user.UserRepository;
import com.eshop.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void create(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public List<User> findAllByListId(List<Long> ids) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean isEnable(Long id, boolean enable) {
        return false;
    }
}
