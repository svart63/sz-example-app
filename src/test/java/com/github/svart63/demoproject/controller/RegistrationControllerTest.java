package com.github.svart63.demoproject.controller;

import com.github.svart63.demoproject.AbstractControllerTest;
import com.github.svart63.demoproject.dto.Registration;
import com.github.svart63.demoproject.model.Profile;
import com.github.svart63.demoproject.model.User;
import com.github.svart63.demoproject.service.ProfileService;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class RegistrationControllerTest extends AbstractControllerTest {

    @Test
    void testRegistrationControllerSuccess() throws Exception {
        Registration login = new Registration();
        String email = "123@domain.com";
        login.setEmail(email);
        login.setPassword("12345678");
        login.setBirthDay(1L);
        login.setLastName("l");
        login.setFirstName("f");
        MockHttpServletResponse response = mockMvc.perform(post("/api/registration")
                .content(json(login))
                .header(CONTENT_TYPE, APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(CREATED.value(), response.getStatus());
        String redirectedUrl = response.getRedirectedUrl();
        assertNotNull(redirectedUrl, "Redirect url mustn't be null");
        long profileId = Long.parseLong(redirectedUrl.substring(redirectedUrl.lastIndexOf('/') + 1));
        Optional<Profile> profileOptional = profileService.findOne(profileId);
        assertThat(profileOptional).hasValueSatisfying(profile -> {
            assertThat(profile.getFirstName()).isEqualTo("f");
            assertThat(profile.getLastName()).isEqualTo("l");
            assertThat(profile.getBirthDay()).isEqualTo(1L);
        });
        Optional<User> byEmail = userService.findByEmail(email);
        assertThat(byEmail).isNotEmpty();
    }
}