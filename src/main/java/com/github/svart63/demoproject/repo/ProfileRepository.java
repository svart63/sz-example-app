package com.github.svart63.demoproject.repo;

import com.github.svart63.demoproject.model.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProfileRepository extends BaseRepo<Profile> {
    Optional<Profile> findByUserId(long userId);
}
