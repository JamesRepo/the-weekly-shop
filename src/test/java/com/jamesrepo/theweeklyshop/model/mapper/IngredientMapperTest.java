package com.jamesrepo.theweeklyshop.model.mapper;

import com.jamesrepo.theweeklyshop.model.Ingredient;
import com.jamesrepo.theweeklyshop.model.dto.command.IngredientCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.IngredientQuery;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class IngredientMapperTest {

    private IngredientMapper ingredientMapper;

    @BeforeEach
    public void setUp() {
        ingredientMapper = new IngredientMapper();
    }

    @Test
    public void testToQuery_NullIngredient() {
        IngredientQuery result = ingredientMapper.toQuery(null);
        assertNull(result, "Result should be null when input Ingredient is null");
    }

    @Test
    public void testToQuery_EmptyIngredient() {
        Ingredient ingredient = new Ingredient();
        IngredientQuery result = ingredientMapper.toQuery(ingredient);

        assertNotNull(result, "Result should not be null");
        assertNull(result.getName(), "Name should be null");
        assertNull(result.getUnitOfMeasurement(), "UnitOfMeasurement should be null");
        assertNull(result.getId(), "ID should be null");
    }

    @Test
    public void testToQuery_WithIngredientData() {
        UUID ingredientId = UUID.randomUUID();

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientId);
        ingredient.setName("Test Ingredient");
        ingredient.setUnitOfMeasurement("grams");

        IngredientQuery result = ingredientMapper.toQuery(ingredient);

        assertNotNull(result, "Result should not be null");
        assertEquals(ingredient.getName(), result.getName(), "Names should match");
        assertEquals(ingredient.getUnitOfMeasurement(), result.getUnitOfMeasurement(), "UnitOfMeasurements should match");
        assertEquals(ingredient.getId(), result.getId(), "IDs should match");
    }

    @Test
    public void testToEntity_NullIngredientCommand() {
        Ingredient result = ingredientMapper.toEntity(null);
        assertNull(result, "Result should be null when input IngredientCommand is null");
    }

    @Test
    public void testToEntity_EmptyIngredientCommand() {
        IngredientCommand command = new IngredientCommand();
        Ingredient result = ingredientMapper.toEntity(command);

        assertNotNull(result, "Result should not be null");
        assertNull(result.getName(), "Name should be null");
        assertNull(result.getUnitOfMeasurement(), "UnitOfMeasurement should be null");
    }

    @Test
    public void testToEntity_WithIngredientCommandData() {
        IngredientCommand command = new IngredientCommand();
        command.setName("Test Ingredient Command");
        command.setUnitOfMeasurement("liters");

        Ingredient result = ingredientMapper.toEntity(command);

        assertNotNull(result, "Result should not be null");
        assertEquals(command.getName(), result.getName(), "Names should match");
        assertEquals(command.getUnitOfMeasurement(), result.getUnitOfMeasurement(), "UnitOfMeasurements should match");
    }
}
