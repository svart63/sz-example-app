package com.github.svart63.demoproject.service;

import com.github.svart63.demoproject.model.auth.SimpleUserDetails;
import com.github.svart63.demoproject.repo.auth.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserDetailsService implements UserDetailsService {
    private LoginRepository loginRepository;

    @Autowired
    public SimpleUserDetailsService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return loginRepository.findByEmail(email)
                .map(SimpleUserDetails::new)
                .orElseThrow(() -> new IllegalArgumentException("User not found by email: " + email));
    }
}
