package com.eshop.userservice.facade.impl;

import com.eshop.userservice.dto.UserRegistrationDto;
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
        return UserRegistrationDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegistrationDto data = (UserRegistrationDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (data.email().length() < 6 || data.email().length() > 32) {
            errors.rejectValue("email", "Size.authForm.email");
        }
        if (userService.existsByEmail(data.email())) {
            errors.rejectValue("email", "Duplicate.authForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (data.password().length() < 8 || data.password().length() > 32) {
            errors.rejectValue("password", "Size.authForm.password");
        }

        if (!data.passwordConfirm().equals(data.password())) {
            errors.rejectValue("passwordConfirm", "Diff.authForm.passwordConfirm");
        }
    }
}
