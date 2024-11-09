package com.jamesrepo.theweeklyshop.model.dto.command;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class RecipeIngredientCommand {

    private UUID ingredient;
    private BigDecimal quantity;
}
