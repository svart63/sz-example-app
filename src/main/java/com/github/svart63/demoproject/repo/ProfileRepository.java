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

    Optional<Profile> findIdByUserId(long id);
}
