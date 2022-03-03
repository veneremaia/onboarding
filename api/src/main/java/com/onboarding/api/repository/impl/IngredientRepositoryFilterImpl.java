package com.onboarding.api.repository.impl;

import com.onboarding.api.repository.entity.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class IngredientRepositoryFilterImpl {

    private EntityManager entityManager;

    public IngredientRepositoryFilterImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Page<Ingredient> getAllByOptionalFilter(Pageable pageable, Map<String, String> filters) {
        String queryPart = "SELECT p FROM Ingredient p ";
        if (!filters.isEmpty()) {
            queryPart += "WHERE ";
            for (String key : filters.keySet()) {
                queryPart += "p." + key + " LIKE '%" + filters.get(key) + "%' AND ";
            }
            queryPart = queryPart.substring(0, queryPart.length() - 3);
        }
        List<Object[]> queryResult = entityManager.createQuery(queryPart)
                .getResultList();

        List<Ingredient> result = new ArrayList<>();
        for (Object ingredient : queryResult) {
            result.add((Ingredient) ingredient);
        }

        return new PageImpl<>(result, pageable, result.size());
    }
}
