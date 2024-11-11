package com.jamesrepo.theweeklyshop.model.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.jamesrepo.theweeklyshop.model.*;
import com.jamesrepo.theweeklyshop.model.dto.command.*;
import com.jamesrepo.theweeklyshop.model.dto.query.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

public class RecipeMapperTest {

    private RecipeMapper recipeMapper;

    @BeforeEach
    public void setUp() {
        recipeMapper = new RecipeMapper();
    }

    @Test
    public void testToQuery_NullRecipe() {
        RecipeQuery result = recipeMapper.toQuery(null);
        assertNull(result, "Result should be null when input Recipe is null");
    }

    @Test
    public void testToQuery_EmptyRecipe() {
        Recipe recipe = new Recipe();
        RecipeQuery result = recipeMapper.toQuery(recipe);

        assertNotNull(result, "Result should not be null");
        assertNull(result.getName(), "Name should be null");
        assertNull(result.getDescription(), "Description should be null");
        assertNull(result.getInstructions(), "Instructions should be null");
        assertNull(result.getRecipeIngredients(), "RecipeIngredients should be null");
        assertNull(result.getId(), "ID should be null");
    }

    @Test
    public void testToQuery_WithRecipeData() {
        UUID recipeId = UUID.randomUUID();
        UUID ingredientId = UUID.randomUUID();

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientId);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setId(UUID.randomUUID());
        recipeIngredient.setQuantity(BigDecimal.valueOf(2.0));
        recipeIngredient.setIngredient(ingredient);

        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.setName("Test Recipe");
        recipe.setDescription("Test Description");
        recipe.setInstructions("Test Instructions");
        recipe.setRecipeIngredients(Collections.singletonList(recipeIngredient));

        RecipeQuery result = recipeMapper.toQuery(recipe);

        assertNotNull(result, "Result should not be null");
        assertEquals(recipe.getName(), result.getName(), "Names should match");
        assertEquals(recipe.getDescription(), result.getDescription(), "Descriptions should match");
        assertEquals(recipe.getInstructions(), result.getInstructions(), "Instructions should match");
        assertEquals(recipe.getId(), result.getId(), "IDs should match");

        List<RecipeIngredientQuery> ingredientQueries = result.getRecipeIngredients();
        assertNotNull(ingredientQueries, "RecipeIngredients should not be null");
        assertEquals(1, ingredientQueries.size(), "There should be one RecipeIngredient");

        RecipeIngredientQuery ingredientQuery = ingredientQueries.get(0);
        assertEquals(recipeIngredient.getId(), ingredientQuery.getId(), "Ingredient IDs should match");
        assertEquals(ingredient.getId(), ingredientQuery.getIngredientId(), "Ingredient IDs should match");
        assertEquals(recipeIngredient.getQuantity(), ingredientQuery.getQuantity(), "Quantities should match");
    }

    @Test
    public void testToEntity_NullRecipeCommand() {
        Recipe result = recipeMapper.toEntity(null);
        assertNull(result, "Result should be null when input RecipeCommand is null");
    }

    @Test
    public void testToEntity_EmptyRecipeCommand() {
        RecipeCommand command = new RecipeCommand();
        Recipe result = recipeMapper.toEntity(command);

        assertNotNull(result, "Result should not be null");
        assertNull(result.getName(), "Name should be null");
        assertNull(result.getDescription(), "Description should be null");
        assertNull(result.getInstructions(), "Instructions should be null");
        assertNull(result.getRecipeIngredients(), "RecipeIngredients should be null");
    }

    @Test
    public void testToEntity_WithRecipeCommandData() {
        UUID ingredientId = UUID.randomUUID();

        RecipeIngredientCommand recipeIngredientCommand = new RecipeIngredientCommand();
        recipeIngredientCommand.setQuantity(BigDecimal.valueOf(3.5));
        recipeIngredientCommand.setIngredient(ingredientId);

        RecipeCommand command = new RecipeCommand();
        command.setName("Test Recipe Command");
        command.setDescription("Test Description Command");
        command.setInstructions("Test Instructions Command");
        command.setRecipeIngredients(Collections.singletonList(recipeIngredientCommand));

        Recipe result = recipeMapper.toEntity(command);

        assertNotNull(result, "Result should not be null");
        assertEquals(command.getName(), result.getName(), "Names should match");
        assertEquals(command.getDescription(), result.getDescription(), "Descriptions should match");
        assertEquals(command.getInstructions(), result.getInstructions(), "Instructions should match");

        List<RecipeIngredient> recipeIngredients = result.getRecipeIngredients();
        assertNotNull(recipeIngredients, "RecipeIngredients should not be null");
        assertEquals(1, recipeIngredients.size(), "There should be one RecipeIngredient");

        RecipeIngredient recipeIngredient = recipeIngredients.get(0);
        assertEquals(recipeIngredientCommand.getQuantity(), recipeIngredient.getQuantity(), "Quantities should match");
        assertNotNull(recipeIngredient.getIngredient(), "Ingredient should not be null");
        assertEquals(ingredientId, recipeIngredient.getIngredient().getId(), "Ingredient IDs should match");
        assertEquals(result, recipeIngredient.getRecipe(), "Recipe should be set in RecipeIngredient");
    }

    @Test
    public void testToEntity_WithNullRecipeIngredients() {
        RecipeCommand command = new RecipeCommand();
        command.setName("Test Recipe Command");
        command.setDescription("Test Description Command");
        command.setInstructions("Test Instructions Command");
        command.setRecipeIngredients(null);

        Recipe result = recipeMapper.toEntity(command);

        assertNotNull(result, "Result should not be null");
        assertNull(result.getRecipeIngredients(), "RecipeIngredients should be null");
    }

    @Test
    public void testToQuery_WithNullRecipeIngredients() {
        Recipe recipe = new Recipe();
        recipe.setName("Test Recipe");
        recipe.setDescription("Test Description");
        recipe.setInstructions("Test Instructions");
        recipe.setRecipeIngredients(null);

        RecipeQuery result = recipeMapper.toQuery(recipe);

        assertNotNull(result, "Result should not be null");
        assertNull(result.getRecipeIngredients(), "RecipeIngredients should be null");
    }
}

