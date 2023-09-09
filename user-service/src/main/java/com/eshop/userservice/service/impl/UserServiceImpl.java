package com.eshop.userservice.service.impl;

import com.eshop.userservice.models.BaseUser;
import com.eshop.userservice.repository.user.UserRepository;
import com.eshop.userservice.service.CustomUserDetails;
import com.eshop.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(CustomUserDetails user) {
        log.info("Creating new User {}", user.getEmail());
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("This user already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save((BaseUser) user);

    }

    @Override
    public void update(CustomUserDetails user) {

        String oldUserPassword = user.getPassword();

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("This email already exist");
        } else {
            user.setEmail(user.getEmail());
        }

        // а как нахуй новый пароль сравинть с старым? М??

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void delete(Long id) {

    }

    @Override
    public CustomUserDetails findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public CustomUserDetails findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User by this email not found"));
    }

    public List<CustomUserDetails> findAllByListId(List<Long> ids) {
        return Collections.singletonList((BaseUser) userRepository.findAllById(ids));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    public List<CustomUserDetails> findAll() {
        return Collections.singletonList((BaseUser) userRepository.findAll());
    }

    @Override
    public boolean isEnable(Long id, boolean enable) {
        userRepository.updateUserEnabledById(id, enable);
        return enable;
    }
}
