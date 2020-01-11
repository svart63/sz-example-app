package com.github.svart63.demoproject.controller;

import com.github.svart63.demoproject.model.User;
import com.github.svart63.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Long> login(@RequestParam String email) {
        Optional<User> login = userService.findByEmail(email);
        return login.map(userLogin -> ResponseEntity.ok(userLogin.getId()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
