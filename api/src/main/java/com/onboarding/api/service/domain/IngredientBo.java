package com.onboarding.api.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IngredientBo {

    private Integer id;

    private String description;

    private Double price;
}
