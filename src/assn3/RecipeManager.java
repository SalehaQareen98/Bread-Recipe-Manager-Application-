package assn3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code RecipeManager} class is responsible for managing a collection of recipes.
 * It provides functionality to:
 * - Load recipes from a file.
 * - Retrieve the list of recipes.
 * - Save a shopping list to a file.
 * 
 * This class is designed to encapsulate all operations related to recipe management
 * and facilitate interaction with external files.
 * 
 * @author Saleha Qareen
 * @id 041161192
 * @course CST8284 OOP
 * @assignment Assignment 3
 * @since 2024-12-04
 * @professor Reginald Dyer
 */
public class RecipeManager {
    /**
     * A list to store all recipes managed by this RecipeManager.
     */
    private final List<Recipe> recipesList;

    /**
     * Constructs a new {@code RecipeManager} with an empty list of recipes.
     */
    public RecipeManager() {
        recipesList = new ArrayList<>();
    }

    /**
     * Loads recipes from a specified file. The file should have recipes in the format:
     * <pre>
     * Recipe <RecipeName>
     * <IngredientName> <Amount>
     * ...
     * (empty line to separate recipes)
     * </pre>
     *
     * @param filename the name of the file containing the recipes.
     */
    public void loadRecipes(String filename) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.startsWith("Recipe")) {
                    String name = line.substring(7).trim();
                    Recipe recipe = new Recipe(name);

                    while (fileScanner.hasNextLine()) {
                        line = fileScanner.nextLine().trim();
                        if (line.isEmpty()) break;

                        String[] parts = line.split(" ");
                        if (parts.length == 2) {
                            recipe.addIngredient(parts[0], Float.parseFloat(parts[1]));
                        }
                    }
                    recipesList.add(recipe);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Recipe file not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number in recipe file: " + e.getMessage());
        }
    }

    /**
     * Retrieves a copy of the list of recipes managed by this {@code RecipeManager}.
     *
     * @return a {@code List} of {@code Recipe} objects.
     */
    public List<Recipe> getRecipes() {
        return new ArrayList<>(recipesList);
    }

    /**
     * Saves the provided shopping list to a specified file.
     *
     * @param filename     the name of the file to save the shopping list to.
     * @param shoppingList the shopping list content to be saved.
     */
    public void saveShoppingList(String filename, String shoppingList) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            writer.print(shoppingList);
        } catch (FileNotFoundException e) {
            System.err.println("Error saving shopping list: " + e.getMessage());
        }
    }
}
