package com.jamesrepo.theweeklyshop.view.ingredient;

import com.jamesrepo.theweeklyshop.model.dto.query.IngredientQuery;
import com.jamesrepo.theweeklyshop.service.IngredientService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("ingredients")
public class IngredientView extends VerticalLayout {

    private final IngredientService ingredientService;

    public IngredientView(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
        add(createIngredientGrid());
    }

    private Grid<IngredientQuery> createIngredientGrid() {
        Grid<IngredientQuery> ingredientGrid = new Grid<>(IngredientQuery.class);

        List<IngredientQuery> ingredientQueryList = ingredientService.getAll();

        ingredientGrid.setItems(ingredientQueryList);

        ingredientGrid.setColumns("name", "unitOfMeasurement");

        ingredientGrid.getColumnByKey("name").setHeader("Ingredient Name");
        ingredientGrid.getColumnByKey("unitOfMeasurement").setHeader("Unit of Measurement");

        return ingredientGrid;
    }
}
