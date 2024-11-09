package com.jamesrepo.theweeklyshop.model.mapper;

import com.jamesrepo.theweeklyshop.model.Ingredient;
import com.jamesrepo.theweeklyshop.model.Recipe;
import com.jamesrepo.theweeklyshop.model.RecipeIngredient;
import com.jamesrepo.theweeklyshop.model.dto.command.RecipeCommand;
import com.jamesrepo.theweeklyshop.model.dto.command.RecipeIngredientCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.RecipeIngredientQuery;
import com.jamesrepo.theweeklyshop.model.dto.query.RecipeQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeMapper {


    public RecipeQuery toQuery(Recipe recipe) {
        if (recipe == null) {
            return null;
        }
        RecipeQuery recipeQuery = new RecipeQuery();
        recipeQuery.setName(recipe.getName());
        recipeQuery.setDescription(recipe.getDescription());
        recipeQuery.setInstructions(recipe.getInstructions());
        recipeQuery.setRecipeIngredients(toRecipeIngredientQueryList(recipe.getRecipeIngredients()));
        recipeQuery.setId(recipe.getId());
        return recipeQuery;
    }

    private List<RecipeIngredientQuery> toRecipeIngredientQueryList(List<RecipeIngredient> recipeIngredients) {
        if (recipeIngredients == null) {
            return null;
        }
        return recipeIngredients.stream()
                .map(this::toRecipeIngredientQuery)
                .toList();
    }

    private RecipeIngredientQuery toRecipeIngredientQuery(RecipeIngredient ingredient) {
        if (ingredient == null) {
            return null;
        }
        RecipeIngredientQuery ingredientQuery = new RecipeIngredientQuery();
        ingredientQuery.setIngredientId(ingredient.getIngredient().getId());
        ingredientQuery.setQuantity(ingredient.getQuantity());
        ingredientQuery.setId(ingredient.getId());
        return ingredientQuery;
    }


    public Recipe toEntity(RecipeCommand recipeCommand) {
        if (recipeCommand == null) {
            return null;
        }
        Recipe recipe = new Recipe();
        recipe.setName(recipeCommand.getName());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setInstructions(recipeCommand.getInstructions());
        recipe.setRecipeIngredients(toRecipeIngredientList(recipe, recipeCommand.getRecipeIngredients()));
        return recipe;
    }

    private List<RecipeIngredient> toRecipeIngredientList(
            Recipe recipe,
            List<RecipeIngredientCommand> recipeIngredients
    ) {
        if (recipeIngredients == null) {
            return null;
        }
        return recipeIngredients.stream()
                .map(recipeIngredient -> toRecipeIngredient(recipe, recipeIngredient))
                .toList();
    }

    private RecipeIngredient toRecipeIngredient(Recipe recipe, RecipeIngredientCommand recipeIngredientCommand) {
        if (recipeIngredientCommand == null) {
            return null;
        }
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setQuantity(recipeIngredientCommand.getQuantity());

        Ingredient ingredient = new Ingredient();
        ingredient.setId(recipeIngredientCommand.getIngredient());

        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setRecipe(recipe);
        return recipeIngredient;
    }
}