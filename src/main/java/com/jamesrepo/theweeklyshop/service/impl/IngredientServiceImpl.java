package com.jamesrepo.theweeklyshop.service.impl;

import com.jamesrepo.theweeklyshop.dao.IngredientRepository;
import com.jamesrepo.theweeklyshop.model.Ingredient;
import com.jamesrepo.theweeklyshop.model.dto.command.IngredientCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.IngredientQuery;
import com.jamesrepo.theweeklyshop.model.mapper.IngredientMapper;
import com.jamesrepo.theweeklyshop.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Override
    public IngredientQuery get(UUID id) {
        log.info("Getting Ingredient with ID: {}", id);
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
        return ingredientMapper.toQuery(ingredient);
    }

    @Override
    public IngredientQuery post(IngredientCommand command) {
        log.info("Creating Ingredient: {}", command.getName());
        Ingredient ingredient = ingredientMapper.toEntity(command);
        ingredientRepository.save(ingredient);
        return ingredientMapper.toQuery(ingredient);
    }

    @Override
    public IngredientQuery put(UUID id, IngredientCommand command) {
        log.info("Updating Ingredient with ID: {}", id);
        Ingredient ingredient = ingredientMapper.toEntity(command);
        ingredient.setId(id);
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return ingredientMapper.toQuery(savedIngredient);
    }

    @Override
    public UUID delete(UUID id) {
        log.info("Deleting Ingredient with ID: {}", id);
        return findAndDeleteIngredient(id).orElse(null);
    }

    private Optional<UUID> findAndDeleteIngredient(UUID id) {
        return ingredientRepository.findById(id)
                .map(ingredient -> {
                    ingredientRepository.delete(ingredient);
                    return id;
                });
    }
}
