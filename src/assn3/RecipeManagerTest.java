package assn3;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * RecipeManagerTest is the main application class for managing and interacting
 * with recipes in the Recipe Manager application. It provides a user-friendly
 * interface for displaying available recipes, creating a shopping list by
 * selecting recipes and their quantities, and displaying or saving the shopping list.
 * 
 * Features include:
 * - Displaying recipes.
 * - Adding or adjusting quantities for recipes in a shopping list.
 * - Compiling and printing a shopping list with total required ingredients.
 * - Saving the shopping list to a file.
 * 
 * This class serves as the entry point for the application and demonstrates the use
 * of object-oriented principles like encapsulation and interaction between multiple classes.
 * 
 * @author Saleha Qareen
 * @id 041161192
 * @course CST8284 OOP
 * @assignment Assignment 3
 * @since 2024-12-04
 * @professor Reginald Dyer
 */
public class RecipeManagerTest {
    /**
     * The RecipeManager instance used to manage and interact with recipes.
     */
    private RecipeManager manager;

    /**
     * Scanner instance for reading user input from the console.
     */
    private Scanner inputScanner;

    /**
     * Array to store the selected quantities for each recipe.
     * The index of the array corresponds to the index of a recipe in the list of recipes.
     */
    private int[] selectedQuantities;


	/**
	 * Constructor to initialize RecipeManagerTest with a RecipeManager instance.
	 *
	 * @param manager RecipeManager instance to manage recipes.
	 */
	public RecipeManagerTest(RecipeManager manager) {
		this.manager = manager;
		this.inputScanner = new Scanner(System.in);
		this.selectedQuantities = new int[manager.getRecipes().size()];
	}

	/**
	 * The entry point for the application, replacing the main method in Assignment3.
	 *
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) {
		RecipeManager manager = new RecipeManager();
		manager.loadRecipes("recipes.txt"); // Load recipes from the file
		RecipeManagerTest app = new RecipeManagerTest(manager);
		app.start(); // Launch the Recipe Manager interface
	}

	/**
	 * Initiates the Recipe Manager interface and handles user interaction.
	 */
	public void start() {
		System.out.println("Welcome to Saleha's Recipe Manager.");

		boolean continueRunning = true;
		while (continueRunning) {
			displayMenu();
			int userChoice = getInputWithinRange(4);

			if (userChoice == 1) {
				showRecipes();
			} else if (userChoice == 2) {
				createRecipeShoppingList();
			} else if (userChoice == 3) {
				displayShoppingList();
			} else if (userChoice == 4) {
				continueRunning = false;
			} else if (userChoice == 0) {
				// Do nothing, as the menu will redisplay automatically
			} else {
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	/**
	 * Displays the main menu options to the user.
	 */
	private void displayMenu() {
		System.out.println("Please select one of the following options:");
		System.out.println("1. Show available recipes.");
		System.out.println("2. Create Shopping List.");
		System.out.println("3. Print Shopping List.");
		System.out.println("4. Quit Program.");
		System.out.println("0. to reprint this menu.");
		System.out.print("Please enter your choice: ");
	}

	/**
	 * Displays the list of recipes with their indices.
	 */
	private void showRecipes() {
		List<Recipe> recipes = manager.getRecipes();
		System.out.println("Available Recipes:");
		for (int i = 0; i < recipes.size(); i++) {
			System.out.println((i + 1) + ". " + recipes.get(i).getName());
		}
	}

	/**
	 * Adds or reduces a recipe's quantity in the shopping list.
	 */
	private void createRecipeShoppingList() {
		System.out.print("Which bread would you like? ");
		int recipeIndex = getInputWithinRange(manager.getRecipes().size()) - 1;

		System.out.print("How much of this bread would you like? ");
		int quantityChange = getValidQuantityChange();

		// Ensure quantity does not drop below zero
		int updatedQuantity = selectedQuantities[recipeIndex] + quantityChange;
		if (updatedQuantity < 0) {
			System.out.println("Please only type digits.\r\n"
					+ "Valid input are digits from 0 to " + manager.getRecipes().size());
			return; // Exit without updating the quantity
		}

		// Update the quantity
		selectedQuantities[recipeIndex] = updatedQuantity;
	}

	/**
	 * Displays the compiled shopping list and saves it if the user opts to.
	 */
	private void displayShoppingList() {
		StringBuilder shoppingSummary = new StringBuilder("Shopping List:\n");
		Map<String, Integer> ingredientTotals = new HashMap<>();

		List<Recipe> recipes = manager.getRecipes();
		for (int i = 0; i < recipes.size(); i++) {
			if (selectedQuantities[i] > 0) {
				shoppingSummary.append(selectedQuantities[i])
						.append(" ")
						.append(recipes.get(i).getName())
						.append(" loaf/loaves.\n");

				Map<String, Float> ingredients = recipes.get(i).getIngredients();
				for (Map.Entry<String, Float> ingredient : ingredients.entrySet()) {
					int totalAmount = ingredientTotals.getOrDefault(ingredient.getKey(), 0);
					ingredientTotals.put(ingredient.getKey(), totalAmount + (int) (ingredient.getValue() * selectedQuantities[i]));
				}
			}
		}

		shoppingSummary.append("\nYou will need a total of:\n");
		for (Map.Entry<String, Integer> totalIngredient : ingredientTotals.entrySet()) {
			shoppingSummary.append(totalIngredient.getValue())
					.append(" ")
					.append(totalIngredient.getKey())
					.append("\n");
		}

		System.out.println(shoppingSummary.toString());

		System.out.print("Do you want to save this list (Y/n)? ");
		if (inputScanner.nextLine().trim().equalsIgnoreCase("Y")) {
			manager.saveShoppingList("shoppinglist.txt", shoppingSummary.toString());
			System.out.println("Shopping list saved.");
		}
	}

	/**
	 * Gets a valid menu choice within the provided range.
	 */
	private int getInputWithinRange(int maxChoice) {
		int choice = -1;
		while (choice < 0 || choice > maxChoice) {
			try {
				choice = Integer.parseInt(inputScanner.nextLine());
				if (choice < 0 || choice > maxChoice) {
					System.out.println("Please only type digits. Valid input are digits from 0 to " + maxChoice + ".");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please only type digits. Valid input are digits from 0 to " + maxChoice + ".");
			}
		}
		return choice;
	}

	/**
	 * Gets a valid quantity change from the user.
	 * Allows both positive and negative values.
	 */
	private int getValidQuantityChange() {
		while (true) {
			try {
				return Integer.parseInt(inputScanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}
	}
}
