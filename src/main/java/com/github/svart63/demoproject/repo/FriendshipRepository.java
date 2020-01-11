package com.github.svart63.demoproject.repo;

import com.github.svart63.demoproject.model.Friendship;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends BaseRepo<Friendship> {

    //    @Query("select fp.friend from Friendship fp where fp.friend = :profileId")
    List<Friendship> findFriendsByProfileId(long profileId);

    boolean existsByProfileIdAndFriendId(long profileId, long friendId);
}
