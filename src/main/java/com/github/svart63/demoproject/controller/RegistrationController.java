package com.github.svart63.demoproject.controller;

import com.github.svart63.demoproject.model.Role;
import com.github.svart63.demoproject.model.User;
import com.github.svart63.demoproject.service.RegistrationService;
import com.github.svart63.demoproject.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/registration")
@Slf4j
public class RegistrationController {
    private final RegistrationService registrationService;
    private final RoleService roleService;

    public RegistrationController(RegistrationService registrationService, RoleService roleService) {
        this.registrationService = registrationService;
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<String> registration(@RequestBody User login) {
        try {
            login.setRole(roleService.findByName("USER")
                    .orElseGet(() -> roleService.save(new Role("USER"))));
            this.registrationService.registration(login);
            return ResponseEntity.created(URI.create("/login.html")).build();
        } catch (Exception e) {
            log.error("Registration failed", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
