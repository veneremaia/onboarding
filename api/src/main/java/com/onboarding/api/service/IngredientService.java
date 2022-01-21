package com.onboarding.api.service;

import com.onboarding.api.repository.IngredientRepository;
import com.onboarding.api.repository.entity.Ingredient;
import com.onboarding.api.service.domain.IngredientBo;
import com.onboarding.api.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository repository;

    public List<IngredientBo> findAll() {
        return repository.findAll().stream().map(this::mapToBo).collect(Collectors.toList());
    }

    public IngredientBo findById(Integer id) {
        return repository.findById(id)
                .map(this::mapToBo)
                .orElseThrow(() -> new NotFoundException(String.format("Ingrediente con id %s no existe", id)));
    }

    private IngredientBo mapToBo(Ingredient entity) {
        return new IngredientBo(entity.getId(), entity.getDescription());
    }
}
