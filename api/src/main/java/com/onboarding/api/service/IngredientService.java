package com.onboarding.api.service;

import com.onboarding.api.service.domain.IngredientBo;

import java.util.List;
import java.util.Optional;

public interface IngredientService {

    List<IngredientBo> findAll(int page, int size, String sort, Optional<String> filter);

    IngredientBo findById(Integer id);

    IngredientBo add(IngredientBo ingredient);
}
