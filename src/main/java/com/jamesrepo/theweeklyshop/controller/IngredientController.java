package com.jamesrepo.theweeklyshop.controller;

import com.jamesrepo.theweeklyshop.model.dto.command.IngredientCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.IngredientQuery;
import com.jamesrepo.theweeklyshop.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<IngredientQuery> get(
            @PathVariable String id
    ) {
        IngredientQuery ingredientQuery = ingredientService.get(UUID.fromString(id));
        return new ResponseEntity<>(ingredientQuery, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IngredientQuery> post(
            @RequestBody IngredientCommand ingredientCommand
    ) {
        IngredientQuery savedIngredient = ingredientService.post(ingredientCommand);
        return new ResponseEntity<>(savedIngredient, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<IngredientQuery> put(
            @PathVariable String id,
            @RequestBody IngredientCommand ingredientCommand
    ) {
        IngredientQuery savedIngredient = ingredientService.put(UUID.fromString(id), ingredientCommand);
        return new ResponseEntity<>(savedIngredient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> delete(
            @PathVariable String id
    ) {
        UUID deletedId = ingredientService.delete(UUID.fromString(id));
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

}
