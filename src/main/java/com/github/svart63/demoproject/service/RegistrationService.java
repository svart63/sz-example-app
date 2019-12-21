package com.github.svart63.demoproject.service;

import com.github.svart63.demoproject.model.UserLogin;
import com.github.svart63.demoproject.repo.RegistrationRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Pattern;

@Service
public class RegistrationService extends BaseService<UserLogin, RegistrationRepository> {
    private Pattern emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
            "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])" +
            "*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?" +
            "|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
            "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");

    @Autowired
    public RegistrationService(RegistrationRepository repo) {
        super(repo);
    }

    public void registration(UserLogin login) {
        if (login == null) {
            throw new IllegalArgumentException("User data is not provided");
        }
        if (!emailPattern.matcher(login.getEmail()).matches()) {
            throw new IllegalArgumentException("Invalid email address");
        }
        if (StringUtils.isBlank(login.getPassword()) || login.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be longer that 6 symbols and can't be only spaces");
        }
        if (repo.existsByEmail(login.getEmail())) {
            throw new IllegalArgumentException("User already exists");
        }
        String md5Hex = new String(DigestUtils.md5Digest(login.getPassword().getBytes()));
        login.setPassword(md5Hex);
        repo.save(login);
    }

    public UserLogin findByEmail(String email) {
        return repo.findByEmail(email);
    }
}
