package com.jamesrepo.theweeklyshop.controller;

import com.jamesrepo.theweeklyshop.model.dto.RecipeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<RecipeDto> get(
            @PathVariable String id
            ) {
        return new ResponseEntity<>(new RecipeDto(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecipeDto> post(
            @RequestBody RecipeDto recipeDto
    ) {
        return new ResponseEntity<>(new RecipeDto(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RecipeDto> put(
            @RequestBody RecipeDto recipeDto
    ) {
        return new ResponseEntity<>(new RecipeDto(), HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<UUID> delete(
            @PathVariable UUID uuid
    ) {
        return new ResponseEntity<>(UUID.randomUUID(), HttpStatus.OK);
    }
}
