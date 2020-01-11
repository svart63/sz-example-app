package com.github.svart63.demoproject.controller;

import com.github.svart63.demoproject.AbstractControllerTest;
import com.github.svart63.demoproject.model.Profile;
import com.github.svart63.demoproject.model.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
class ProfileControllerTest extends AbstractControllerTest {

    private static final String EMAIL = "add@domain.com";

    @Test
    @WithMockUser(value = EMAIL, password = "123456")
    @Transactional
    void testAddFriend() throws Exception {
        createProfile(EMAIL);
        Profile second = createProfile("test@mail.com");
        mockMvc.perform(post("/api/friendship/friends/add/" + second.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private Profile createProfile(String s) {
        Profile profile = new Profile();
        profile.setBirthDay(0L);
        profile.setFirstName("S");
        profile.setFirstName("Z");
        User user = new User();
        user.setPassword("123456");
        user.setPassword(s);
        profile.setUser(user);
        profileService.save(profile);
        return profile;
    }
}