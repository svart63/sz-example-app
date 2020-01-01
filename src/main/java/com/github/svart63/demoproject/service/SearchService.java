package com.github.svart63.demoproject.service;

import com.github.svart63.demoproject.model.Profile;
import com.github.svart63.demoproject.repo.SearchRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {
    private SearchRepository repository;
    private EntityManager entityManager;

    public SearchService(SearchRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    public List<Profile> find(Map<String, String> fields) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Profile> query = builder.createQuery(Profile.class);
        Root<Profile> root = query.from(Profile.class);
        Predicate[] predicates = fields.entrySet().stream()
                .map(e -> {
                    Path<String> expression = root.get(e.getKey());
                    return builder.like(builder.lower(expression), '%' + StringUtils.lowerCase(e.getValue()) + '%');
                })
                .toArray(Predicate[]::new);
        return repository.find(query.select(root.get("firstName").get("lastName").get("id"))
                .where(predicates));
    }

}
