package com.onboarding.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {

    private Integer id;

    @NotNull(message = "description is mandatory")
    private String description;

    @NotNull(message = "price is mandatory")
    private Double price;
}
