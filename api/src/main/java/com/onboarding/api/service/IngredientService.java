package com.onboarding.api.service;

import com.onboarding.api.service.domain.IngredientBo;

import java.util.List;

public interface IngredientService {

    List<IngredientBo> findAll();

    IngredientBo findById(Integer id);

    IngredientBo add(IngredientBo ingredient);
}
