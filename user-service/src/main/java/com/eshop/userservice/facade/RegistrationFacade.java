package com.eshop.userservice.facade;
import com.eshop.userservice.dto.ResponseDto;
import com.eshop.userservice.dto.UserLoginDto;
import com.eshop.userservice.dto.UserRegistrationDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface RegistrationFacade {

    void registration(UserRegistrationDto userRegistrationDtO);
    ResponseDto login(UserLoginDto userLoginDto);
    void logout(HttpServletRequest request, HttpServletResponse response);
}
