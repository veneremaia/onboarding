package com.onboarding.api.repository;

import com.onboarding.api.repository.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientJPARepository extends JpaRepository<Ingredient, Integer> {
}
