package com.jamesrepo.theweeklyshop.model.dto.query;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RecipeQuery {

    private UUID id;
    private String name;
    private String description;
    private String instructions;
    private List<RecipeIngredientQuery> recipeIngredients;
}
