package assn3;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code Recipe} class represents a recipe with a name and a list of ingredients,
 * where each ingredient is associated with its quantity.
 * 
 * This class provides methods to:
 * - Get the name of the recipe.
 * - Add ingredients to the recipe.
 * - Retrieve the list of ingredients with their quantities.
 * 
 * It uses a {@code Map} to store ingredient details, ensuring efficient lookup and manipulation.
 * 
 * @author Saleha Qareen
 * @id 041161192
 * @course CST8284 OOP
 * @assignment Assignment 3
 * @since 2024-12-04
 * @professor Reginald Dyer
 */
public class Recipe {
    /**
     * The name of the recipe.
     */
    private final String recipeName;

    /**
     * A map containing ingredient details, where the key is the ingredient name
     * and the value is the amount required for the recipe.
     */
    private final Map<String, Float> ingredientDetails;

    /**
     * Constructs a new {@code Recipe} with the specified name.
     *
     * @param recipeName the name of the recipe.
     */
    public Recipe(String recipeName) {
        this.recipeName = recipeName;
        this.ingredientDetails = new HashMap<>();
    }

    /**
     * Gets the name of the recipe.
     *
     * @return the name of the recipe.
     */
    public String getName() {
        return recipeName;
    }

    /**
     * Adds an ingredient to the recipe with the specified quantity.
     * If the ingredient already exists, its quantity is updated.
     *
     * @param ingredientName the name of the ingredient.
     * @param amount the quantity of the ingredient required for the recipe.
     */
    public void addIngredient(String ingredientName, float amount) {
        ingredientDetails.put(ingredientName, amount);
    }

    /**
     * Retrieves the list of ingredients and their quantities for the recipe.
     *
     * @return a copy of the map containing ingredient names and their quantities.
     */
    public Map<String, Float> getIngredients() {
        return new HashMap<>(ingredientDetails);
    }
}
