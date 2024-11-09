package com.jamesrepo.theweeklyshop.service.impl;

import com.jamesrepo.theweeklyshop.dao.RecipeRepository;
import com.jamesrepo.theweeklyshop.model.Recipe;
import com.jamesrepo.theweeklyshop.model.dto.command.RecipeCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.RecipeQuery;
import com.jamesrepo.theweeklyshop.model.mapper.RecipeMapper;
import com.jamesrepo.theweeklyshop.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;

    @Override
    public RecipeQuery get(UUID id) {
        log.info("Getting Recipe with ID: {}", id);
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        return recipeMapper.toQuery(recipe);
    }

    @Override
    public RecipeQuery post(RecipeCommand command) {
        log.info("Creating Recipe: {}", command.getName());
        Recipe recipe = recipeMapper.toEntity(command);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return recipeMapper.toQuery(savedRecipe);
    }

    @Override
    public RecipeQuery put(UUID id, RecipeCommand command) {
        return null;
    }

    @Override
    public UUID delete(UUID id) {
        return null;
    }
}
