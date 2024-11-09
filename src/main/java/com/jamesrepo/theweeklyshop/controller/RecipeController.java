package com.jamesrepo.theweeklyshop.controller;

import com.jamesrepo.theweeklyshop.model.dto.command.RecipeCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.RecipeQuery;
import com.jamesrepo.theweeklyshop.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<RecipeQuery> get(
            @PathVariable String id
            ) {
        RecipeQuery recipeQuery = recipeService.get(UUID.fromString(id));
        return new ResponseEntity<>(recipeQuery, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecipeQuery> post(
            @RequestBody RecipeCommand recipeCommand
    ) {
        RecipeQuery savedRecipe = recipeService.post(recipeCommand);
        return new ResponseEntity<>(savedRecipe, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RecipeQuery> put(
            @PathVariable String id,
            @RequestBody RecipeCommand recipeCommand
    ) {
        RecipeQuery savedRecipe = recipeService.put(UUID.fromString(id), recipeCommand);
        return new ResponseEntity<>(savedRecipe, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> delete(
            @PathVariable String id
    ) {
        UUID deletedId = recipeService.delete(UUID.fromString(id));
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }
}
