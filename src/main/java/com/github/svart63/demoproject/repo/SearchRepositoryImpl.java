package com.github.svart63.demoproject.repo;

import com.github.svart63.demoproject.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class SearchRepositoryImpl implements SearchRepository {
    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Profile> find(CriteriaQuery<Profile> query) {
        return entityManager.createQuery(query).getResultList();
    }
}
