package com.jamesrepo.theweeklyshop.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.jamesrepo.theweeklyshop.dao.IngredientRepository;
import com.jamesrepo.theweeklyshop.model.Ingredient;
import com.jamesrepo.theweeklyshop.model.dto.command.IngredientCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.IngredientQuery;
import com.jamesrepo.theweeklyshop.model.mapper.IngredientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

public class IngredientServiceImplTest {

    private IngredientRepository ingredientRepository;
    private IngredientMapper ingredientMapper;
    private IngredientServiceImpl ingredientService;

    @BeforeEach
    public void setUp() {
        ingredientRepository = mock(IngredientRepository.class);
        ingredientMapper = mock(IngredientMapper.class);
        ingredientService = new IngredientServiceImpl(ingredientRepository, ingredientMapper);
    }

    @Test
    public void testGetIngredientById() {
        UUID id = UUID.randomUUID();
        Ingredient ingredient = new Ingredient();
        IngredientQuery ingredientQuery = new IngredientQuery();

        when(ingredientRepository.findById(id)).thenReturn(Optional.of(ingredient));
        when(ingredientMapper.toQuery(ingredient)).thenReturn(ingredientQuery);

        IngredientQuery result = ingredientService.get(id);

        assertNotNull(result, "Result should not be null");
        assertEquals(ingredientQuery, result, "Returned IngredientQuery should match the expected value");

        verify(ingredientRepository).findById(id);
        verify(ingredientMapper).toQuery(ingredient);
    }

    @Test
    public void testGetIngredientById_NotFound() {
        UUID id = UUID.randomUUID();

        when(ingredientRepository.findById(id)).thenReturn(Optional.empty());
        when(ingredientMapper.toQuery(null)).thenReturn(null);

        IngredientQuery result = ingredientService.get(id);

        assertNull(result, "Result should be null when ingredient is not found");

        verify(ingredientRepository).findById(id);
        verify(ingredientMapper).toQuery(null);
    }

    @Test
    public void testPostIngredient() {
        IngredientCommand command = new IngredientCommand();
        command.setName("Test Ingredient");
        Ingredient ingredient = new Ingredient();
        IngredientQuery ingredientQuery = new IngredientQuery();

        when(ingredientMapper.toEntity(command)).thenReturn(ingredient);
        when(ingredientMapper.toQuery(ingredient)).thenReturn(ingredientQuery);

        IngredientQuery result = ingredientService.post(command);

        assertNotNull(result, "Result should not be null");
        assertEquals(ingredientQuery, result, "Returned IngredientQuery should match the expected value");

        verify(ingredientMapper).toEntity(command);
        verify(ingredientRepository).save(ingredient);
        verify(ingredientMapper).toQuery(ingredient);
    }

    @Test
    public void testPutIngredient() {
        UUID id = UUID.randomUUID();
        IngredientCommand command = new IngredientCommand();
        command.setName("Updated Ingredient");
        Ingredient ingredient = new Ingredient();
        Ingredient savedIngredient = new Ingredient();
        IngredientQuery ingredientQuery = new IngredientQuery();

        when(ingredientMapper.toEntity(command)).thenReturn(ingredient);
        when(ingredientRepository.save(ingredient)).thenReturn(savedIngredient);
        when(ingredientMapper.toQuery(savedIngredient)).thenReturn(ingredientQuery);

        IngredientQuery result = ingredientService.put(id, command);

        assertNotNull(result, "Result should not be null");
        assertEquals(ingredientQuery, result, "Returned IngredientQuery should match the expected value");
        assertEquals(id, ingredient.getId(), "Ingredient ID should be set correctly");

        verify(ingredientMapper).toEntity(command);
        verify(ingredientRepository).save(ingredient);
        verify(ingredientMapper).toQuery(savedIngredient);
    }

    @Test
    public void testDeleteIngredient() {
        UUID id = UUID.randomUUID();
        Ingredient ingredient = new Ingredient();

        when(ingredientRepository.findById(id)).thenReturn(Optional.of(ingredient));

        UUID result = ingredientService.delete(id);

        assertNotNull(result, "Result should not be null");
        assertEquals(id, result, "Returned ID should match the deleted ingredient's ID");

        verify(ingredientRepository).findById(id);
        verify(ingredientRepository).delete(ingredient);
    }

    @Test
    public void testDeleteIngredient_NotFound() {
        UUID id = UUID.randomUUID();

        when(ingredientRepository.findById(id)).thenReturn(Optional.empty());

        UUID result = ingredientService.delete(id);

        assertNull(result, "Result should be null when ingredient is not found");

        verify(ingredientRepository).findById(id);
        verify(ingredientRepository, never()).delete(any(Ingredient.class));
    }
}
