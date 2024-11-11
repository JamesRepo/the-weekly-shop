package com.jamesrepo.theweeklyshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamesrepo.theweeklyshop.model.dto.command.IngredientCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.IngredientQuery;
import com.jamesrepo.theweeklyshop.service.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private IngredientController ingredientController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetIngredient() throws Exception {
        UUID id = UUID.randomUUID();
        IngredientQuery ingredientQuery = new IngredientQuery();
        ingredientQuery.setId(id);
        ingredientQuery.setName("Test Ingredient");
        ingredientQuery.setUnitOfMeasurement("grams");

        when(ingredientService.get(id)).thenReturn(ingredientQuery);

        mockMvc.perform(get("/api/ingredient/{id}", id.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Test Ingredient"))
                .andExpect(jsonPath("$.unitOfMeasurement").value("grams"));

        verify(ingredientService).get(id);
    }

    @Test
    public void testPostIngredient() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setName("New Ingredient");
        command.setUnitOfMeasurement("liters");

        IngredientQuery ingredientQuery = new IngredientQuery();
        ingredientQuery.setId(UUID.randomUUID());
        ingredientQuery.setName(command.getName());
        ingredientQuery.setUnitOfMeasurement(command.getUnitOfMeasurement());

        when(ingredientService.post(any(IngredientCommand.class))).thenReturn(ingredientQuery);

        mockMvc.perform(post("/api/ingredient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ingredientQuery.getId().toString()))
                .andExpect(jsonPath("$.name").value(ingredientQuery.getName()))
                .andExpect(jsonPath("$.unitOfMeasurement").value(ingredientQuery.getUnitOfMeasurement()));

        ArgumentCaptor<IngredientCommand> captor = ArgumentCaptor.forClass(IngredientCommand.class);
        verify(ingredientService).post(captor.capture());
        assertEquals(command.getName(), captor.getValue().getName());
        assertEquals(command.getUnitOfMeasurement(), captor.getValue().getUnitOfMeasurement());
    }

    @Test
    public void testPutIngredient() throws Exception {
        UUID id = UUID.randomUUID();
        IngredientCommand command = new IngredientCommand();
        command.setName("Updated Ingredient");
        command.setUnitOfMeasurement("kilograms");

        IngredientQuery ingredientQuery = new IngredientQuery();
        ingredientQuery.setId(id);
        ingredientQuery.setName(command.getName());
        ingredientQuery.setUnitOfMeasurement(command.getUnitOfMeasurement());

        when(ingredientService.put(eq(id), any(IngredientCommand.class))).thenReturn(ingredientQuery);

        mockMvc.perform(put("/api/ingredient/{id}", id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value(ingredientQuery.getName()))
                .andExpect(jsonPath("$.unitOfMeasurement").value(ingredientQuery.getUnitOfMeasurement()));

        ArgumentCaptor<IngredientCommand> captor = ArgumentCaptor.forClass(IngredientCommand.class);
        verify(ingredientService).put(eq(id), captor.capture());
        assertEquals(command.getName(), captor.getValue().getName());
        assertEquals(command.getUnitOfMeasurement(), captor.getValue().getUnitOfMeasurement());
    }

    @Test
    public void testDeleteIngredient() throws Exception {
        UUID id = UUID.randomUUID();

        when(ingredientService.delete(id)).thenReturn(id);

        mockMvc.perform(delete("/api/ingredient/{id}", id.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("\"" + id + "\""));

        verify(ingredientService).delete(id);
    }
}
