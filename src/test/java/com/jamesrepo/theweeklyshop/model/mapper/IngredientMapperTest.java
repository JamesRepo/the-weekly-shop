package com.jamesrepo.theweeklyshop.model.mapper;

import com.jamesrepo.theweeklyshop.model.Ingredient;
import com.jamesrepo.theweeklyshop.model.dto.command.IngredientCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.IngredientQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IngredientMapperTest {

    @Autowired
    IngredientMapper ingredientMapper;

    @Test
    public void toQuery_withNullIngredient_returnsNull() {
        assertNull(ingredientMapper.toQuery(null));
    }

    @Test
    public void toQuery_withValidIngredient_returnsEquivalentIngredientQuery() {
        Ingredient ingredient = new Ingredient();
        UUID randomUUID = UUID.randomUUID();
        ingredient.setId(randomUUID);
        ingredient.setName("Sample Ingredient");
        ingredient.setUnitOfMeasurement("g");

        IngredientQuery resultQuery = ingredientMapper.toQuery(ingredient);

        assertNotNull(resultQuery);
        assertEquals(ingredient.getName(), resultQuery.getName());
        assertEquals(ingredient.getUnitOfMeasurement(), resultQuery.getUnitOfMeasurement());
        assertEquals(ingredient.getId(), resultQuery.getId());
    }

    @Test
    public void toEntity_withNullIngredientDto_returnsNull() {
        assertNull(ingredientMapper.toEntity(null));
    }

    @Test
    public void toEntity_withValidIngredientDto_returnsEquivalentIngredient() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setName("Sample Ingredient");
        ingredientCommand.setUnitOfMeasurement("g");

        Ingredient resultIngredient = ingredientMapper.toEntity(ingredientCommand);

        assertNotNull(resultIngredient);
        assertEquals(ingredientCommand.getName(), resultIngredient.getName());
        assertEquals(ingredientCommand.getUnitOfMeasurement(), resultIngredient.getUnitOfMeasurement());
    }

}
