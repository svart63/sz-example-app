package com.github.svart63.demoproject.controller;

import com.github.svart63.demoproject.model.User;
import com.github.svart63.demoproject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<Long> login(@RequestParam String email) {
        Optional<User> login = loginService.findByEmail(email);
        return login.map(userLogin -> ResponseEntity.ok(userLogin.getId()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
