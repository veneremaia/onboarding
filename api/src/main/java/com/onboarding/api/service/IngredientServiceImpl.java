package com.onboarding.api.service;

import com.onboarding.api.repository.IngredientRepository;
import com.onboarding.api.repository.entity.Ingredient;
import com.onboarding.api.repository.impl.IngredientRepositoryFilterImpl;
import com.onboarding.api.service.domain.IngredientBo;
import com.onboarding.api.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repository;

    private final IngredientRepositoryFilterImpl repositoryFilter;

    private final String EQUALS = "=";

    private final String DIVIDER = ";";

    @Override
    public List<IngredientBo> findAll(int page, int size, String sort, Optional<String> filters) {
        Map<String, String> mapFilter = filters.map(this::mapToFilters).orElse(new HashMap<>());
        return repositoryFilter.getAllByOptionalFilter(PageRequest.of(page, size, Sort.by(sort)), mapFilter)
                .getContent().stream().map(this::mapToBo).collect(Collectors.toList());
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
        return new IngredientBo(entity.getId(), entity.getDescription(), entity.getPrice(), entity.getExpirationDate());
    }

    private Ingredient mapToEntity(IngredientBo bo) {
        return new Ingredient(bo.getId(), bo.getDescription(), bo.getPrice(), bo.getExpirationDate());
    }

    private Map<String, String> mapToFilters(String filters) {
        Map<String, String> result = new HashMap<>();
        Arrays.stream(filters.split(DIVIDER))
                .collect(Collectors.toList())
                .forEach(filter -> result.put(filter.split(EQUALS)[0], filter.split(EQUALS)[1]));
        return result;
    }
}
