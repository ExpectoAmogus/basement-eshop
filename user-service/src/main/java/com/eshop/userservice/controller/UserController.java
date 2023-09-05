package com.eshop.userservice.controller;

import com.eshop.userservice.facade.UserFacade;
import com.eshop.userservice.models.User;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    UserFacade userFacade;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userFacade.findAll());
    }

    @GetMapping
    public String index() {
        return "pages/user/panel";
    }

    @PostMapping
    public String saveRole(Model model){

        return "pages/admin/panel";
    }


    //???????????????????

}
