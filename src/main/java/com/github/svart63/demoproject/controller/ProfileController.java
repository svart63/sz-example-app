package com.github.svart63.demoproject.controller;

import com.github.svart63.demoproject.model.Profile;
import com.github.svart63.demoproject.model.auth.SimpleUserDetails;
import com.github.svart63.demoproject.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PutMapping
    public ResponseEntity<Profile> update(@RequestBody Profile profile) {
        //TODO trap for QA, will they found that we can update another user or PUT will create new one profile?
        return ResponseEntity.ok(profileService.save(profile));
    }

    @GetMapping("{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable("id") long id) {
        return ResponseEntity.of(profileService.findOne(id));
    }

    @GetMapping
    public ResponseEntity<Profile> getProfile(@AuthenticationPrincipal SimpleUserDetails user) {
        return ResponseEntity.of(profileService.findByUserId(user.getId()));
    }
}
