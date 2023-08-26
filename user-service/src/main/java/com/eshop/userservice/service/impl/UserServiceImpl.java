package com.eshop.userservice.service.impl;

import com.eshop.userservice.models.entity.User;
import com.eshop.userservice.repository.user.UserRepository;
import com.eshop.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(User user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("This user already exist");
        }
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Override
    public void update(User user) {

        String oldUserPassword = user.getPassword();

        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("This email already exist");
        } else {
            user.setEmail(user.getEmail());
        }

        // а как нахуй новый пароль сравинть с старым? М??

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return (User) userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User by this email not found"));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")

    public List<User> findAllByListId(List<Long> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean isEnable(Long id, boolean enable) {
        userRepository.updateUserEnabledById(id,enable);
        return enable;
    }
}
