package com.github.svart63.demoproject.controller;

import com.github.svart63.demoproject.AbstractControllerTest;
import com.github.svart63.demoproject.model.Profile;
import com.github.svart63.demoproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProfileControllerTest extends AbstractControllerTest {

    private static final String EMAIL = "add@domain.com";

    @Test
    @WithMockUser(value = EMAIL, password = "123456")
    void testAddFriend() throws Exception {
        User user = new User();
        user.setEmail(EMAIL);
        user.setPassword("123456");
        registrationService.registration(user);

        Profile first = createProfile();
        Profile second = createProfile();
        mockMvc.perform(post("/api/profile/friends/add/").servletPath(String.valueOf(second.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private Profile createProfile() {
        Profile profile = new Profile();
        profile.setBirthDay(0L);
        profile.setFirstName("S");
        profile.setFirstName("Z");
        User user = registrationService.findByEmail(EMAIL).orElseThrow(() -> new IllegalStateException("User not found"));
        profile.setUser(user);
        profileService.save(profile);
        return profile;
    }
}