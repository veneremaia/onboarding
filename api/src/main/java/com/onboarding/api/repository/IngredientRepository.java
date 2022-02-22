package com.onboarding.api.repository;

import com.onboarding.api.repository.entity.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IngredientRepository {

    Page<Ingredient> findAll(Pageable pageable);

    Optional<Ingredient> findById(Integer id);

    Ingredient save(Ingredient ingredient);

}
