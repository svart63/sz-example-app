package com.github.svart63.demoproject.service;

import com.github.svart63.demoproject.model.Profile;
import com.github.svart63.demoproject.model.Role;
import com.github.svart63.demoproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProfileServiceTest {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private RoleService roleService;

    @Test
    @Transactional
    void testAddFriend() {
        User first = registrationService.registration(newUser("first@dmn.com"));
        User second = registrationService.registration(newUser("second@dmn.com"));
        Profile firstProfile = newProfile(first);
        Profile secondProfile = newProfile(second);
        profileService.addFriend(firstProfile.getId(), secondProfile.getId());
        List<Profile> profileId = profileService.findFriendsByProfileId(firstProfile.getId());
        assertThat(profileId).isNotEmpty().contains(secondProfile);
    }

    private Profile newProfile(User first) {
        Profile profile = new Profile();
        profile.setUser(first);
        profile.setFirstName(first.getEmail());
        return profileService.save(profile);
    }

    private User newUser(String email) {
        User login = new User();
        login.setEmail(email);
        login.setPassword("123456");
        login.setRole(roleService.findByName("USER")
                .orElseGet(() -> roleService.save(new Role("USER"))));
        return login;
    }
}