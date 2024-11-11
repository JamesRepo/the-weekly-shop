package com.jamesrepo.theweeklyshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamesrepo.theweeklyshop.model.dto.command.RecipeCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.RecipeQuery;
import com.jamesrepo.theweeklyshop.service.RecipeService;
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

public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetRecipe() throws Exception {
        UUID id = UUID.randomUUID();
        RecipeQuery recipeQuery = new RecipeQuery();
        recipeQuery.setId(id);
        recipeQuery.setName("Test Recipe");
        recipeQuery.setDescription("Test Description");
        recipeQuery.setInstructions("Test Instructions");
        // Add any additional fields as necessary

        when(recipeService.get(id)).thenReturn(recipeQuery);

        mockMvc.perform(get("/api/recipe/{id}", id.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Test Recipe"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.instructions").value("Test Instructions"));

        verify(recipeService).get(id);
    }

    @Test
    public void testPostRecipe() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setName("New Recipe");
        command.setDescription("New Description");
        command.setInstructions("New Instructions");
        // Set any additional fields as necessary

        RecipeQuery recipeQuery = new RecipeQuery();
        recipeQuery.setId(UUID.randomUUID());
        recipeQuery.setName(command.getName());
        recipeQuery.setDescription(command.getDescription());
        recipeQuery.setInstructions(command.getInstructions());
        // Set any additional fields as necessary

        when(recipeService.post(any(RecipeCommand.class))).thenReturn(recipeQuery);

        mockMvc.perform(post("/api/recipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(recipeQuery.getId().toString()))
                .andExpect(jsonPath("$.name").value(recipeQuery.getName()))
                .andExpect(jsonPath("$.description").value(recipeQuery.getDescription()))
                .andExpect(jsonPath("$.instructions").value(recipeQuery.getInstructions()));

        ArgumentCaptor<RecipeCommand> captor = ArgumentCaptor.forClass(RecipeCommand.class);
        verify(recipeService).post(captor.capture());
        assertEquals(command.getName(), captor.getValue().getName());
        assertEquals(command.getDescription(), captor.getValue().getDescription());
        assertEquals(command.getInstructions(), captor.getValue().getInstructions());
    }

    @Test
    public void testPutRecipe() throws Exception {
        UUID id = UUID.randomUUID();
        RecipeCommand command = new RecipeCommand();
        command.setName("Updated Recipe");
        command.setDescription("Updated Description");
        command.setInstructions("Updated Instructions");
        // Set any additional fields as necessary

        RecipeQuery recipeQuery = new RecipeQuery();
        recipeQuery.setId(id);
        recipeQuery.setName(command.getName());
        recipeQuery.setDescription(command.getDescription());
        recipeQuery.setInstructions(command.getInstructions());
        // Set any additional fields as necessary

        when(recipeService.put(eq(id), any(RecipeCommand.class))).thenReturn(recipeQuery);

        mockMvc.perform(put("/api/recipe/{id}", id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value(recipeQuery.getName()))
                .andExpect(jsonPath("$.description").value(recipeQuery.getDescription()))
                .andExpect(jsonPath("$.instructions").value(recipeQuery.getInstructions()));

        ArgumentCaptor<RecipeCommand> captor = ArgumentCaptor.forClass(RecipeCommand.class);
        verify(recipeService).put(eq(id), captor.capture());
        assertEquals(command.getName(), captor.getValue().getName());
        assertEquals(command.getDescription(), captor.getValue().getDescription());
        assertEquals(command.getInstructions(), captor.getValue().getInstructions());
    }

    @Test
    public void testDeleteRecipe() throws Exception {
        UUID id = UUID.randomUUID();

        when(recipeService.delete(id)).thenReturn(id);

        mockMvc.perform(delete("/api/recipe/{id}", id.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("\"" + id + "\""));

        verify(recipeService).delete(id);
    }
}

