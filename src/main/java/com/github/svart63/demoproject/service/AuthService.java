package com.github.svart63.demoproject.service;

import com.github.svart63.demoproject.model.User;
import com.github.svart63.demoproject.repo.auth.AuthRepository;
import org.springframework.util.DigestUtils;

import java.util.Optional;

public abstract class AuthService<R extends AuthRepository> extends BaseService<User, R> {
    public AuthService(R repo) {
        super(repo);
    }

    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    protected String passToMd5(User login) {
        return new String(DigestUtils.md5Digest(login.getPassword().getBytes()));
    }
}
