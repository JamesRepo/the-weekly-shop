package com.jamesrepo.theweeklyshop.service;

import com.jamesrepo.theweeklyshop.model.dto.command.RecipeCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.RecipeQuery;

public interface RecipeService extends PersistenceService<RecipeQuery, RecipeCommand> {}
