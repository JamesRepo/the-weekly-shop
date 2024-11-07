package com.jamesrepo.theweeklyshop.model.mapper;

import com.jamesrepo.theweeklyshop.model.Ingredient;
import com.jamesrepo.theweeklyshop.model.dto.command.IngredientCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.IngredientQuery;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {

    public IngredientQuery toQuery(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }
        IngredientQuery ingredientQuery = new IngredientQuery();
        ingredientQuery.setName(ingredient.getName());
        ingredientQuery.setUnitOfMeasurement(ingredient.getUnitOfMeasurement());
        return ingredientQuery;
    }

    public Ingredient toEntity(IngredientCommand ingredientDto) {
        if (ingredientDto == null) {
            return null;
        }
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDto.getName());
        ingredient.setUnitOfMeasurement(ingredientDto.getUnitOfMeasurement());
        return ingredient;
    }
}
