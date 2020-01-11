package com.github.svart63.demoproject.service;

import com.github.svart63.demoproject.model.Friendship;
import com.github.svart63.demoproject.model.Profile;
import com.github.svart63.demoproject.repo.FriendshipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendshipService extends BaseService<Friendship, FriendshipRepository> {

    private final ProfileService profileService;

    public FriendshipService(FriendshipRepository repo, ProfileService profileService) {
        super(repo);
        this.profileService = profileService;
    }

    public void addFriend(long profileId, long friendId) {
        boolean exists = repo.existsByProfileIdAndFriendId(profileId, friendId);
        if (exists) {
            return;
        }
        Profile profile = profileService.findOne(profileId)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found by id" + profileId));

        Profile friend = profileService.findOne(friendId)
                .orElseThrow(() -> new IllegalArgumentException("Friend not found by id" + friendId));
        Friendship friendship = new Friendship();
        friendship.setProfile(profile);
        friendship.setFriend(friend);
        repo.save(friendship);
    }

    public List<Profile> findFriendsByProfileId(long id) {
        return repo.findFriendsByProfileId(id).stream().map(Friendship::getFriend).collect(Collectors.toList());
    }


}
