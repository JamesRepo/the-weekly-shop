package com.jamesrepo.theweeklyshop.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.jamesrepo.theweeklyshop.dao.RecipeRepository;
import com.jamesrepo.theweeklyshop.model.Recipe;
import com.jamesrepo.theweeklyshop.model.dto.command.RecipeCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.RecipeQuery;
import com.jamesrepo.theweeklyshop.model.mapper.RecipeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.UUID;

public class RecipeServiceImplTest {

    private RecipeRepository recipeRepository;
    private RecipeMapper recipeMapper;
    private RecipeServiceImpl recipeService;

    @BeforeEach
    public void setUp() {
        recipeRepository = mock(RecipeRepository.class);
        recipeMapper = mock(RecipeMapper.class);
        recipeService = new RecipeServiceImpl(recipeMapper, recipeRepository);
    }

    @Test
    public void testGetRecipeById() {
        UUID id = UUID.randomUUID();
        Recipe recipe = new Recipe();
        RecipeQuery recipeQuery = new RecipeQuery();

        when(recipeRepository.findById(id)).thenReturn(Optional.of(recipe));
        when(recipeMapper.toQuery(recipe)).thenReturn(recipeQuery);

        RecipeQuery result = recipeService.get(id);

        assertNotNull(result, "Result should not be null");
        assertEquals(recipeQuery, result, "Returned RecipeQuery should match the expected value");

        verify(recipeRepository).findById(id);
        verify(recipeMapper).toQuery(recipe);
    }

    @Test
    public void testGetRecipeById_NotFound() {
        UUID id = UUID.randomUUID();

        when(recipeRepository.findById(id)).thenReturn(Optional.empty());
        when(recipeMapper.toQuery(null)).thenReturn(null);

        RecipeQuery result = recipeService.get(id);

        assertNull(result, "Result should be null when recipe is not found");

        verify(recipeRepository).findById(id);
        verify(recipeMapper).toQuery(null);
    }

    @Test
    public void testPostRecipe() {
        RecipeCommand command = new RecipeCommand();
        command.setName("Test Recipe");
        Recipe recipe = new Recipe();
        Recipe savedRecipe = new Recipe();
        RecipeQuery recipeQuery = new RecipeQuery();

        when(recipeMapper.toEntity(command)).thenReturn(recipe);
        when(recipeRepository.save(recipe)).thenReturn(savedRecipe);
        when(recipeMapper.toQuery(savedRecipe)).thenReturn(recipeQuery);

        RecipeQuery result = recipeService.post(command);

        assertNotNull(result, "Result should not be null");
        assertEquals(recipeQuery, result, "Returned RecipeQuery should match the expected value");

        verify(recipeMapper).toEntity(command);
        verify(recipeRepository).save(recipe);
        verify(recipeMapper).toQuery(savedRecipe);
    }

    @Test
    public void testPutRecipe() {
        UUID id = UUID.randomUUID();
        RecipeCommand command = new RecipeCommand();
        command.setName("Updated Recipe");
        Recipe recipe = new Recipe();
        Recipe savedRecipe = new Recipe();
        RecipeQuery recipeQuery = new RecipeQuery();

        when(recipeMapper.toEntity(command)).thenReturn(recipe);
        when(recipeRepository.save(recipe)).thenReturn(savedRecipe);
        when(recipeMapper.toQuery(savedRecipe)).thenReturn(recipeQuery);

        RecipeQuery result = recipeService.put(id, command);

        assertNotNull(result, "Result should not be null");
        assertEquals(recipeQuery, result, "Returned RecipeQuery should match the expected value");
        assertEquals(id, recipe.getId(), "Recipe ID should be set correctly");

        verify(recipeMapper).toEntity(command);
        verify(recipeRepository).save(recipe);
        verify(recipeMapper).toQuery(savedRecipe);
    }

    @Test
    public void testDeleteRecipe() {
        UUID id = UUID.randomUUID();
        Recipe recipe = new Recipe();

        when(recipeRepository.findById(id)).thenReturn(Optional.of(recipe));

        UUID result = recipeService.delete(id);

        assertNotNull(result, "Result should not be null");
        assertEquals(id, result, "Returned ID should match the deleted recipe's ID");

        verify(recipeRepository).findById(id);
        verify(recipeRepository).delete(recipe);
    }

    @Test
    public void testDeleteRecipe_NotFound() {
        UUID id = UUID.randomUUID();

        when(recipeRepository.findById(id)).thenReturn(Optional.empty());

        UUID result = recipeService.delete(id);

        assertNull(result, "Result should be null when recipe is not found");

        verify(recipeRepository).findById(id);
        verify(recipeRepository, never()).delete(any(Recipe.class));
    }
}
