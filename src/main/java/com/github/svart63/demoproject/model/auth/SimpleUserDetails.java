package com.github.svart63.demoproject.model.auth;

import com.github.svart63.demoproject.model.UserLogin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class SimpleUserDetails implements UserDetails {
    private UserLogin userLogin;

    public long getId() {
        return userLogin.getId();
    }

    public SimpleUserDetails(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        return userLogin.getPassword();
    }

    @Override
    public String getUsername() {
        return userLogin.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
