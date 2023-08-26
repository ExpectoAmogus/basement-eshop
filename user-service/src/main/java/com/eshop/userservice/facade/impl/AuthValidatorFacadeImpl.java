package com.eshop.userservice.facade.impl;

import com.eshop.userservice.dto.AuthDto;
import com.eshop.userservice.facade.AuthValidatorFacade;
import com.eshop.userservice.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Service
public class AuthValidatorFacadeImpl implements AuthValidatorFacade {

    private final UserService userService;

    public AuthValidatorFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AuthDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuthDto data = (AuthDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (data.getEmail().length() < 6 || data.getEmail().length() > 32) {
            errors.rejectValue("email", "Size.authForm.email");
        }
        if (userService.existsByEmail(data.getEmail())) {
            errors.rejectValue("email", "Duplicate.authForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (data.getPassword().length() < 8 || data.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.authForm.password");
        }

        if (!data.getPasswordConfirm().equals(data.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.authForm.passwordConfirm");
        }
    }
}
