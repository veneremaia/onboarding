package com.onboarding.api.controller;

import com.onboarding.api.controller.dto.IngredientDto;
import com.onboarding.api.service.IngredientService;
import com.onboarding.api.service.domain.IngredientBo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private static final Logger LOG = LoggerFactory.getLogger(IngredientController.class);

    private final IngredientService service;

    @GetMapping
    public ResponseEntity<List<IngredientDto>> getAll() {
        List<IngredientDto> result = service.findAll().stream()
                .map(this::mapToDto).collect(Collectors.toList());
        LOG.debug("result -> {}", result);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{ingredientId}")
    public ResponseEntity<IngredientDto> get(@PathVariable(name = "ingredientId") Integer ingredientId) {
        IngredientDto result = mapToDto(service.findById(ingredientId));
        LOG.debug("result -> {}", result);
        return ResponseEntity.ok().body(result);
    }

    private IngredientDto mapToDto(IngredientBo bo) {
        return new IngredientDto(bo.getId(), bo.getDescription());
    }
}
