package com.github.svart63.demoproject.controller;

import com.github.svart63.demoproject.exception.AuthException;
import com.github.svart63.demoproject.model.UserLogin;
import com.github.svart63.demoproject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<String> registration(@RequestBody UserLogin login) {
        try {
            return ResponseEntity.ok(loginService.authToken(login));
        } catch (AuthException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
