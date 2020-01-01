package com.github.svart63.demoproject.repo;

import com.github.svart63.demoproject.model.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Repository
public interface ProfileRepository extends BaseRepo<Profile> {
    Optional<Profile> findByUserId(long userId);

    @Query(nativeQuery = true, value = "select p.first_name, p.last_name, p.id from profile_friends pr\n" +
            "left join profile p on pr.friends_id = p.id\n" +
            "where pr.profile_id = :profileId")
    List<Profile> findFriendsById(long profileId);

    Optional<Profile> findIdByUserId(long id);

    @Modifying
    @Query(nativeQuery = true, value = "insert into profile_friends values(?, ?)")
    void insertFriend(long source, long target);
}
