package com.eshop.userservice.controller;

import com.eshop.userservice.dto.ResponseDto;
import com.eshop.userservice.dto.UserLoginDto;
import com.eshop.userservice.dto.UserRegistrationDto;
import com.eshop.userservice.facade.RegistrationFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final RegistrationFacade registrationFacade;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void registration(@RequestBody UserRegistrationDto request){
        registrationFacade.registration(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto login(@RequestBody UserLoginDto request){
        return registrationFacade.login(request);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        registrationFacade.logout(request, response);
    }
}
