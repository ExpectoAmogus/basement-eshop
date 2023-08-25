package com.eshop.userservice.service.impl;

import com.eshop.userservice.models.entity.User;
import com.eshop.userservice.repository.user.UserRepository;
import com.eshop.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public List<User> findAllByListId(List<Long> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean isEnable(Long id, boolean enable) {
        userRepository.updateUserEnabledById(id,enable);
        return enable;
    }
}
