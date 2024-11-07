package com.jamesrepo.theweeklyshop.model.dto.query;

import com.jamesrepo.theweeklyshop.model.dto.command.IngredientCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class IngredientQuery extends IngredientCommand {

    private UUID id;
}
