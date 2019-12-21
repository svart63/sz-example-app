package com.github.svart63.demoproject.controller;

import com.github.svart63.demoproject.model.UserLogin;
import com.github.svart63.demoproject.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/registration")
@Slf4j
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<String> registration(@RequestBody UserLogin login) {
        try {
            this.registrationService.registration(login);
            return ResponseEntity.created(URI.create("#/login")).build();
        } catch (Exception e) {
            log.error("Registration failed", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
