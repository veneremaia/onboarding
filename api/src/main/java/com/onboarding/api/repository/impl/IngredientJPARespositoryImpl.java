package com.onboarding.api.repository.impl;

import com.onboarding.api.repository.IngredientJPARepository;
import com.onboarding.api.repository.IngredientRepository;
import com.onboarding.api.repository.entity.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "repository", name = "system", havingValue = "ddbb")
public class IngredientJPARespositoryImpl implements IngredientRepository {

    private final IngredientJPARepository repository;

    @Override
    public Page<Ingredient> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Ingredient> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return repository.save(ingredient);
    }
}
