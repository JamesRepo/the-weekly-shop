package com.jamesrepo.theweeklyshop.model.dto.query;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class RecipeIngredientQuery {

    private UUID id;
    private UUID ingredientId;
    private BigDecimal quantity;
}
