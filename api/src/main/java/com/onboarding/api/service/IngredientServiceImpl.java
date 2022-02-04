package com.onboarding.api.service;

import com.onboarding.api.controller.dto.IngredientDto;
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
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repository;

    @Override
    public List<IngredientBo> findAll() {
        return repository.findAll().stream().map(this::mapToBo).collect(Collectors.toList());
    }

    @Override
    public IngredientBo findById(Integer id) {
        return repository.findById(id)
                .map(this::mapToBo)
                .orElseThrow(() -> new NotFoundException(String.format("Ingrediente con id %s no existe", id)));
    }

    @Override
    public IngredientBo add(IngredientBo ingredient) {
        Ingredient saved = repository.save(mapToEntity(ingredient));
        return mapToBo(saved);
    }

    private IngredientBo mapToBo(Ingredient entity) {
        return new IngredientBo(entity.getId(), entity.getDescription(), entity.getPrice(),entity.getExpirationDate());
    }

    private Ingredient mapToEntity(IngredientBo bo) {
        return new Ingredient(bo.getId(), bo.getDescription(), bo.getPrice(),bo.getExpirationDate());
    }
}
