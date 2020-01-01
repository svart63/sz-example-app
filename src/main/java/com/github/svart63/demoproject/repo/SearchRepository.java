package com.github.svart63.demoproject.repo;

import com.github.svart63.demoproject.model.Profile;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public interface SearchRepository {
    List<Profile> find(CriteriaQuery<Profile> query);
}
