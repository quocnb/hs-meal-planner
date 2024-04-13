package mealplanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  static ArrayList<Meal> meals;
  static Scanner scanner;
  public static void main(String[] args) {
    meals = new ArrayList<>();
    scanner = new Scanner(System.in);

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
            if (meals.isEmpty()) {
              System.out.println("No meals saved. Add a meal first.");
            } else {
              meals.forEach(s -> System.out.println(s.toString() + "\n"));
            }
            break;
          default:
            break;
        }
    }
  }

  static void addMeal() {
    // Get type
    String type;

    while (true) {
      System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
      type = scanner.nextLine();
      if (!Meal.validateType(type)) {
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
    meals.add(new Meal(type, name, ingredients));
    System.out.println("The meal has been added!");
  }
}