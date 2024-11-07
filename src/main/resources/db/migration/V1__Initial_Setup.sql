-- V1__Initial_Setup.sql

CREATE TABLE recipe (
    id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    instructions TEXT
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;;

CREATE TABLE ingredient (
    id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    unit_of_measurement VARCHAR(50)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;;

CREATE TABLE recipe_ingredient (
    id BINARY(16) NOT NULL PRIMARY KEY,
    recipe_id BINARY(16) NOT NULL,
    ingredient_id BINARY(16) NOT NULL,
    quantity DECIMAL(10, 2),
    FOREIGN KEY (recipe_id) REFERENCES recipe(id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;;

CREATE TABLE shopping_list (
    id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;;

CREATE TABLE shopping_list_item (
    id BINARY(16) NOT NULL PRIMARY KEY,
    shopping_list_id BINARY(16) NOT NULL,
    ingredient_id BINARY(16) NOT NULL,
    quantity DECIMAL(10, 2),
    FOREIGN KEY (shopping_list_id) REFERENCES shopping_list(id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;;
