package com.github.svart63.demoproject.service;

import com.github.svart63.demoproject.model.Profile;
import com.github.svart63.demoproject.repo.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService extends BaseService<Profile, ProfileRepository> {
    @Autowired
    public ProfileService(ProfileRepository repo) {
        super(repo);
    }

}
