package com.github.svart63.demoproject.service;

import com.github.svart63.demoproject.model.UserLogin;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;


@Profile("test")
@SpringBootTest
class RegistrationServiceTest {
    @Autowired
    private RegistrationService regService;

    @Test
    void testRegistrationSuccess() {
        String email = "user.email@domain.com";
        UserLogin login = newUser(email);
        regService.registration(login);
        UserLogin user = regService.findByEmail(email);
        assertNotNull(user);
        assertEquals(email, user.getEmail());
        assertThat(user.getId(), greaterThan(0L));
    }

    private UserLogin newUser(String email) {
        val login = new UserLogin();
        login.setPassword("123456");
        login.setEmail(email);
        return login;
    }

    @Test
    void testRegistrationFailedWhenEmailIncorrect() {
        String email = "user.email.domain.com";
        UserLogin login = newUser(email);
        assertErrorMessage(login, "Invalid email address");
    }

    private void assertErrorMessage(UserLogin login, String message) {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> regService.registration(login));
        assertEquals(ex.getMessage(), message);
    }

    @Test
    void testRegistrationFailedWhenPassworIsShort() {
        String email = "user.email@domain.com";
        UserLogin login = newUser(email);
        login.setPassword("12345");
        assertErrorMessage(login, "Password must be longer that 6 symbols and can't be only spaces");
    }

    @Test
    void testRegistrationFailedWhenPasswordOnlySpaces() {
        String email = "user.email@domain.com";
        UserLogin login = newUser(email);
        login.setPassword("                       ");
        assertErrorMessage(login, "Password must be longer that 6 symbols and can't be only spaces");
    }

    @Test
    void testRegistrationFailedWhenPasswordIsNull() {
        String email = "user.email@domain.com";
        UserLogin login = newUser(email);
        login.setPassword(null);
        assertErrorMessage(login, "Password must be longer that 6 symbols and can't be only spaces");
    }

    @Test
    void testRegistrationFailedWhenUserAlreadyExists() {
        String email = "alreadyexists@domain.com";
        UserLogin login = newUser(email);
        regService.save(login);
        assertErrorMessage(login, "User already exists");
    }
}