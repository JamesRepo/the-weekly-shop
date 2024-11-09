package com.jamesrepo.theweeklyshop.service.impl;

import com.jamesrepo.theweeklyshop.dao.IngredientRepository;
import com.jamesrepo.theweeklyshop.model.Ingredient;
import com.jamesrepo.theweeklyshop.model.dto.command.IngredientCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.IngredientQuery;
import com.jamesrepo.theweeklyshop.model.mapper.IngredientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private IngredientMapper ingredientMapper;

    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetIngredientFound() {
        UUID id = UUID.randomUUID();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setName("Sugar");

        IngredientQuery ingredientQuery = new IngredientQuery();
        ingredientQuery.setId(id);
        ingredientQuery.setName("Sugar");

        when(ingredientRepository.findById(id)).thenReturn(Optional.of(ingredient));
        when(ingredientMapper.toQuery(ingredient)).thenReturn(ingredientQuery);

        IngredientQuery result = ingredientService.get(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Sugar", result.getName());

        verify(ingredientRepository, times(1)).findById(id);
        verify(ingredientMapper, times(1)).toQuery(ingredient);
    }

    @Test
    public void testGetIngredientNotFound() {
        UUID id = UUID.randomUUID();

        when(ingredientRepository.findById(id)).thenReturn(Optional.empty());
        when(ingredientMapper.toQuery(null)).thenReturn(null);

        IngredientQuery result = ingredientService.get(id);

        assertNull(result);

        verify(ingredientRepository, times(1)).findById(id);
        verify(ingredientMapper, times(1)).toQuery(null);
    }

    @Test
    public void testPostIngredient() {
        IngredientCommand command = new IngredientCommand();
        command.setName("Salt");
        command.setUnitOfMeasurement("tablespoons");

        Ingredient ingredient = new Ingredient();
        ingredient.setName("Salt");
        ingredient.setUnitOfMeasurement("tablespoons");

        Ingredient savedIngredient = new Ingredient();
        savedIngredient.setId(UUID.randomUUID());
        savedIngredient.setName("Salt");
        savedIngredient.setUnitOfMeasurement("tablespoons");

        IngredientQuery ingredientQuery = new IngredientQuery();
        ingredientQuery.setId(savedIngredient.getId());
        ingredientQuery.setName("Salt");
        ingredientQuery.setUnitOfMeasurement("tablespoons");

        when(ingredientMapper.toEntity(command)).thenReturn(ingredient);
        when(ingredientRepository.save(ingredient)).thenReturn(savedIngredient);
        when(ingredientMapper.toQuery(savedIngredient)).thenReturn(ingredientQuery);

        IngredientQuery result = ingredientService.post(command);

        assertNotNull(result);
        assertEquals(savedIngredient.getId(), result.getId());
        assertEquals("Salt", result.getName());

        verify(ingredientMapper, times(1)).toEntity(command);
        verify(ingredientRepository, times(1)).save(ingredient);
        verify(ingredientMapper, times(1)).toQuery(savedIngredient);
    }

    @Test
    public void testPutIngredient() {
        UUID id = UUID.randomUUID();

        IngredientCommand command = new IngredientCommand();
        command.setName("Pepper");

        Ingredient ingredient = new Ingredient();
        ingredient.setName("Pepper");

        Ingredient savedIngredient = new Ingredient();
        savedIngredient.setId(id);
        savedIngredient.setName("Pepper");

        IngredientQuery ingredientQuery = new IngredientQuery();
        ingredientQuery.setId(id);
        ingredientQuery.setName("Pepper");

        when(ingredientMapper.toEntity(command)).thenReturn(ingredient);
        // Simulate setting the ID in the service
        doAnswer(invocation -> {
            ingredient.setId(id);
            return null;
        }).when(ingredientRepository).save(ingredient);

        when(ingredientRepository.save(ingredient)).thenReturn(savedIngredient);
        when(ingredientMapper.toQuery(savedIngredient)).thenReturn(ingredientQuery);

        IngredientQuery result = ingredientService.put(id, command);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Pepper", result.getName());

        verify(ingredientMapper, times(1)).toEntity(command);
        verify(ingredientRepository, times(1)).save(ingredient);
        verify(ingredientMapper, times(1)).toQuery(savedIngredient);
    }

    @Test
    public void testDeleteIngredientFound() {
        UUID id = UUID.randomUUID();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);

        when(ingredientRepository.findById(id)).thenReturn(Optional.of(ingredient));

        UUID result = ingredientService.delete(id);

        assertNotNull(result);
        assertEquals(id, result);

        verify(ingredientRepository, times(1)).findById(id);
        verify(ingredientRepository, times(1)).delete(ingredient);
    }

    @Test
    public void testDeleteIngredientNotFound() {
        UUID id = UUID.randomUUID();

        when(ingredientRepository.findById(id)).thenReturn(Optional.empty());

        UUID result = ingredientService.delete(id);

        assertNull(result);

        verify(ingredientRepository, times(1)).findById(id);
        verify(ingredientRepository, never()).delete(any(Ingredient.class));
    }
}
