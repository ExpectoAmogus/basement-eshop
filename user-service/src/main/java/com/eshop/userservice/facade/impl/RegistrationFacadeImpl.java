package com.eshop.userservice.facade.impl;

import com.eshop.userservice.config.jwt.JwtService;
import com.eshop.userservice.dto.ResponseDto;
import com.eshop.userservice.dto.UserLoginDto;
import com.eshop.userservice.dto.UserRegistrationDto;
import com.eshop.userservice.facade.RegistrationFacade;
import com.eshop.userservice.models.BaseUser;
import com.eshop.userservice.models.User;
import com.eshop.userservice.service.BaseUserService;
import com.eshop.userservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final UserService userService;
    private final BaseUserService<BaseUser> baseUserService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public void registration(UserRegistrationDto userRegistrationDtO) {
        User user = new User();
        user.setEmail(userRegistrationDtO.email());
        user.setPassword(userRegistrationDtO.password());
        userService.create(user);
    }

    @Override
    public ResponseDto login(UserLoginDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        log.info("Auth User with email: {}", request.email());
        var user = baseUserService.findByEmail(request.email());
        var jwtToken = jwtService.generateToken(user);
        return new ResponseDto(jwtToken);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }
}
