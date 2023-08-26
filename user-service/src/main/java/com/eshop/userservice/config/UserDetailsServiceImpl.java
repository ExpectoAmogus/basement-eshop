package com.eshop.userservice.config;

import com.eshop.userservice.models.entity.BaseUser;
import com.eshop.userservice.repository.user.BaseUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final BaseUserRepository<BaseUser> baseUserRepository;

    public UserDetailsServiceImpl(BaseUserRepository<BaseUser> baseUserRepository) {
        this.baseUserRepository = baseUserRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BaseUser baseUser = baseUserRepository.
                findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User nor found"));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(baseUser.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                baseUser.getEmail(),
                baseUser.getPassword(),
                baseUser.getEnabled(),
                true,
                true,
                true,
                grantedAuthorities);
    }
}
