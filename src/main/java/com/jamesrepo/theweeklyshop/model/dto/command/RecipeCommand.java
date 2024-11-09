package com.jamesrepo.theweeklyshop.model.dto.command;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeCommand {

    private String name;
    private String description;
    private String instructions;
    private List<RecipeIngredientCommand> recipeIngredients;
}
