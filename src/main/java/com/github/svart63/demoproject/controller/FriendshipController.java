package com.github.svart63.demoproject.controller;

import com.github.svart63.demoproject.model.Profile;
import com.github.svart63.demoproject.model.auth.SimpleUserDetails;
import com.github.svart63.demoproject.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friendship")
public class FriendshipController {
    private final FriendshipService friendshipService;

    @Autowired
    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @PostMapping("/add/{profileId}")
    public ResponseEntity<?> addFriend(@PathVariable long profileId, @AuthenticationPrincipal SimpleUserDetails user) {
        friendshipService.addFriend(user.getProfileId(), profileId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/friends")
    public List<Profile> getFriends(@RequestParam(defaultValue = "0") long profileId, @AuthenticationPrincipal SimpleUserDetails user) {
        long id = profileId == 0 ? user.getProfileId() : profileId;
        return friendshipService.findFriendsByProfileId(id);
    }
}
