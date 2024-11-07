package com.jamesrepo.theweeklyshop.service;

import com.jamesrepo.theweeklyshop.model.dto.command.IngredientCommand;
import com.jamesrepo.theweeklyshop.model.dto.query.IngredientQuery;

public interface IngredientService extends PersistenceService<IngredientQuery, IngredientCommand> {}
