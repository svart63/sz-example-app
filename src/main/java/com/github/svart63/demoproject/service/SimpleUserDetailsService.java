package com.github.svart63.demoproject.service;

import com.github.svart63.demoproject.model.auth.SimpleUserDetails;
import com.github.svart63.demoproject.repo.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    private ProfileService profileService;

    @Autowired
    public SimpleUserDetailsService(UserRepository userRepository, ProfileService profileService) {
        this.userRepository = userRepository;
        this.profileService = profileService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> {
                    SimpleUserDetails userDetails = new SimpleUserDetails(user);
                    profileService.findProfileIdByUserId(user.getId()).ifPresent(p -> userDetails.setProfileId(p.getId()));
                    return userDetails;
                }).orElseThrow(() -> new IllegalArgumentException("User not found by email: " + email));
    }
}
