package mealplanner;

import java.util.List;
import java.util.Scanner;

public class Main {
  static List<Meal> meals;
  static Scanner scanner;
  static DbManager dbManager;
  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    dbManager = new DbManager();
    meals = dbManager.getMeals();

    while (true) {
        System.out.println("What would you like to do (add, show, exit)?");
        switch (scanner.nextLine()) {
          case "exit":
            System.out.println("Bye!");
            return;
          case "add":
            addMeal();
            break;
          case "show":
            showMeal();
            break;
          default:
            break;
        }
    }
  }

  static void showMeal() {
    while (true) {
      System.out.println("Which category do you want to print (breakfast, lunch, dinner)?");
      String category = scanner.nextLine();
      if (!Meal.validateCategory(category)) {
        System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
        continue;
      }
      List<Meal> mealsInCategory = dbManager.getMeal(category);
      if (mealsInCategory.isEmpty()) {
        System.out.println("No meals found.");
      } else {
        System.out.printf("Category: %s\n\n", category);
        mealsInCategory.forEach(s -> System.out.println(s.toString() + "\n"));
      }
      break;
    }
  }
  static void addMeal() {
    // Get type
    String type;

    while (true) {
      System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
      type = scanner.nextLine();
      if (!Meal.validateCategory(type)) {
        System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
        continue;
      }
      break;
    }
    String name;
    while (true) {
      // Get name
      System.out.println("Input the meal's name:");
      name = scanner.nextLine();
      if (!Meal.validateName(name)) {
        System.out.println("Wrong format. Use letters only!");
        continue;
      }
      break;
    }
    String ingredients;
    while (true) {
      // Get ingredients
      System.out.println("Input the ingredients:");
      ingredients = scanner.nextLine();
      if (!Meal.validateIngredients(ingredients)) {
        System.out.println("Wrong format. Use letters only!");
        continue;
      }
      break;
    }
      // Print result
    Meal meal = new Meal(meals.size() + 1, type, name, ingredients);
    dbManager.addMeal(meal);
    meals.add(meal);
    System.out.println("The meal has been added!");
  }
}