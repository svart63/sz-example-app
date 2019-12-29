package com.github.svart63.demoproject.controller;

import com.github.svart63.demoproject.dto.Registration;
import com.github.svart63.demoproject.model.Profile;
import com.github.svart63.demoproject.model.Role;
import com.github.svart63.demoproject.model.User;
import com.github.svart63.demoproject.service.ProfileService;
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
    private final ProfileService profileService;

    public RegistrationController(RegistrationService registrationService, RoleService roleService, ProfileService profileService) {
        this.registrationService = registrationService;
        this.roleService = roleService;
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<String> registration(@RequestBody Registration registration) {
        try {
            User user = saveUser(registration);
            Profile profile = saveProfile(registration, user);
            return ResponseEntity.created(URI.create("/api/profile/" + profile.getId())).build();
        } catch (Exception e) {
            log.error("Registration failed", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    private User saveUser(@RequestBody Registration registration) {
        User user = new User();
        user.setPassword(registration.getPassword());
        user.setEmail(registration.getEmail());
        user.setRole(roleService.findByName("USER")
                .orElseGet(() -> roleService.save(new Role("USER"))));
        this.registrationService.registration(user);
        return user;
    }

    private Profile saveProfile(@RequestBody Registration registration, User user) {
        Profile profile = new Profile();
        profile.setBirthDay(registration.getBirthDay());
        profile.setFirstName(registration.getFirstName());
        profile.setLastName(registration.getLastName());
        profile.setUser(user);
        return this.profileService.save(profile);
    }
}
