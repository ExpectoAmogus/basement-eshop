package com.eshop.userservice.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.eshop.userservice.models.entity.User;
import com.eshop.userservice.service.UserService;
import org.springframework.stereotype.Service;
import com.eshop.userservice.repository.user.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
   boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    User findByEmail(String email){}

    @Override
    List<User> findAllByListId(List<Long> ids){}

    @Override
    List<User> findAll(){

    }

    @Override
    boolean isEnable(Long id, boolean enable);

    @Override
    public void create(User user) {}

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
}
