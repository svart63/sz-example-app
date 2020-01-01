package com.github.svart63.demoproject.service;

import com.github.svart63.demoproject.model.Profile;
import com.github.svart63.demoproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ComponentScan("com.github.svart63")
class SearchServiceTest {
    @Autowired
    private SearchService searchService;
    @Autowired
    private ProfileService profileService;

    @Test
    @Transactional
    void testFindProfileByName() {
        User user = new User();
        user.setEmail("test@dmn.com");
        user.setPassword("test123");
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setFirstName("FirstName");
        profile.setLastName("LastName");
        Profile saved = profileService.save(profile);
        saved.setUser(null);
        List<Profile> result = searchService.find(Collections.singletonMap("firstName", "first"));
        assertThat(result).containsOnly(saved);
    }
}