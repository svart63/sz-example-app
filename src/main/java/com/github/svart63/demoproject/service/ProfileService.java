package com.github.svart63.demoproject.service;

import com.github.svart63.demoproject.model.Profile;
import com.github.svart63.demoproject.repo.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProfileService extends BaseService<Profile, ProfileRepository> {
    @Autowired
    public ProfileService(ProfileRepository repo) {
        super(repo);
    }

    public Optional<Profile> findByUserId(long userId) {
        return repo.findByUserId(userId);
    }

    public List<Profile> findFriendsByProfileId(long profileId) {
        return repo.findFriendsById(profileId);
    }

    public Optional<Profile> findProfileIdByUserId(long id) {
        return repo.findIdByUserId(id);
    }

    public void addFriend(long source, long target) {
        repo.insertFriend(source, target);
    }
}
