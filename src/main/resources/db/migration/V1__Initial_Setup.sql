-- V1__Initial_Setup.sql

CREATE TABLE Recipe (
    id CHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    instructions TEXT
);

CREATE TABLE Ingredient (
    id CHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    unit_of_measurement VARCHAR(50)
);

CREATE TABLE RecipeIngredient (
    id CHAR(36) NOT NULL PRIMARY KEY,
    recipe_id CHAR(36) NOT NULL,
    ingredient_id CHAR(36) NOT NULL,
    quantity DECIMAL(10, 2),
    FOREIGN KEY (recipe_id) REFERENCES Recipe(id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES Ingredient(id)
);

CREATE TABLE ShoppingList (
    id CHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ShoppingListItem (
    id CHAR(36) NOT NULL PRIMARY KEY,
    shopping_list_id CHAR(36) NOT NULL,
    ingredient_id CHAR(36) NOT NULL,
    quantity DECIMAL(10, 2),
    FOREIGN KEY (shopping_list_id) REFERENCES ShoppingList(id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES Ingredient(id)
);
