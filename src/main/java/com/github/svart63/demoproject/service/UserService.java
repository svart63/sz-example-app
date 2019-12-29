package com.github.svart63.demoproject.service;

import com.github.svart63.demoproject.repo.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AuthService<UserRepository> {

    @Autowired
    public UserService(UserRepository repo) {
        super(repo);
    }

}
