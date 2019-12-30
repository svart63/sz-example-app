package com.github.svart63.demoproject.controller;

import com.github.svart63.demoproject.AbstractControllerTest;
import com.github.svart63.demoproject.dto.Registration;
import com.github.svart63.demoproject.model.Profile;
import com.github.svart63.demoproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                .content(json(login)).contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
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