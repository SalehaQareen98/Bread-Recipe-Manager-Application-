# Bread-Recipe-Manager-Application-


## Overview
The Bread Recipe Manager is a Java-based command-line application designed to assist bakery owners in managing bread recipes and generating ingredient shopping lists. The program dynamically loads recipes from a file, allows users to order bread, calculates ingredient totals, and generates shopping lists while ensuring robust error handling.

## Features
- Load bread recipes dynamically from a text file.
- Display available recipes in a menu-driven interface.
- Add or reduce bread quantities for orders, ensuring total quantities do not go below zero.
- Generate a shopping list with accurate ingredient totals, excluding ingredients with zero quantities.
- Save the shopping list to a file and display it in the console.

## Project Structure
```
BreadRecipeManager/
├── src/                       # Java source files
│   ├── Recipe.java            # Recipe class for managing bread data
│   ├── RecipeManager.java     # Logic for managing recipes and shopping lists
│   ├── RecipeManagerTest.java # Main driver class with menu interaction
├── recipelist.txt             # Input file with bread recipes
├── shoppinglist.txt           # Output file for generated shopping lists
└── README.md                  # Project documentation
```

## How to Run
1. Clone this repository:
   ```bash
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```bash
   cd BreadRecipeManager
   ```
3. Compile the Java source files:
   ```bash
   javac src/*.java
   ```
4. Run the application:
   ```bash
   java src.RecipeManagerTest
   ```
5. Follow the menu options in the terminal to interact with the program.

## How to Generate Javadoc
1. Run the following command in the terminal:
   ```bash
   javadoc -d javadoc src/*.java
   ```
2. Open `javadoc/index.html` in a browser to view the generated documentation.

## Example Usage
1. **Start the program** and view available recipes:
   ```
   Welcome to the Recipe Manager!
   1. Show available recipes.
   2. Create shopping list.
   3. Print and save shopping list.
   4. Quit program.
   0. to reprint this menu.
   ```
2. **Order bread and generate a shopping list:**
   ```
   Enter the number of the bread you want to order: 5
   How much of this bread would you like? 3
   Enter your choice: 3
   You will need a total of:
   6 grams of yeast
   1200 grams of flour
   36 grams of sugar
   18 egg(s)
   ```
## Contact

For questions or feedback, feel free to reach out:

- **Email**: salehaqareen@gmail.com
- **GitHub**: [SalehaQareen98]([https://github.com/your-username](https://github.com/SalehaQareen98))
  
## Skills/Technologies
**Java, File I/O, Exception Handling, Object-Oriented Programming (OOP), CLI Design, System Design.**
