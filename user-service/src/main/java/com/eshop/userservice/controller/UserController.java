package com.eshop.userservice.controller;

import com.eshop.userservice.facade.UserFacade;
import com.eshop.userservice.service.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserFacade userFacade;

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('full','write')")
    public ResponseEntity<List<CustomUserDetails>> getAllUsers() {
        return ResponseEntity.ok().body(userFacade.findAll());
    }
}
