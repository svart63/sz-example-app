package com.github.svart63.demoproject.service;

import com.github.svart63.demoproject.exception.AuthException;
import com.github.svart63.demoproject.model.UserLogin;
import com.github.svart63.demoproject.repo.auth.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class LoginService extends AuthService<LoginRepository> {

    @Autowired
    public LoginService(LoginRepository repo) {
        super(repo);
    }

    public String authToken(UserLogin userLogin) throws AuthException {
        //FIXME there we need validate entered credentials, let's check, will QA find it or not.
        UserLogin login = findByEmail(userLogin.getEmail());
        if (Objects.equals(passToMd5(userLogin), login.getPassword())) {
            return UUID.randomUUID().toString();
        }
        throw new AuthException("Incorrect email or password");
    }

}
