package com.jamesrepo.theweeklyshop.service.impl;

import com.jamesrepo.theweeklyshop.dao.IngredientRepository;
import com.jamesrepo.theweeklyshop.model.Ingredient;
import com.jamesrepo.theweeklyshop.model.dto.command.IngredientCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.IngredientQuery;
import com.jamesrepo.theweeklyshop.model.mapper.IngredientMapper;
import com.jamesrepo.theweeklyshop.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Override
    public IngredientQuery get(UUID id) {
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
        return ingredientMapper.toQuery(ingredient);
    }

    @Override
    public IngredientQuery post(IngredientCommand command) {
        Ingredient ingredient = ingredientMapper.toEntity(command);
        ingredientRepository.save(ingredient);
        return ingredientMapper.toQuery(ingredient);
    }

    @Override
    public IngredientQuery put(UUID id, IngredientCommand command) {
        Ingredient ingredient = ingredientMapper.toEntity(command);
        ingredient.setId(id);
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return ingredientMapper.toQuery(savedIngredient);
    }

    @Override
    public UUID delete(UUID id) {
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
