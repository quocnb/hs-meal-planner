package mealplanner;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Get type
    System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
    String type = scanner.nextLine();
    // Get name
    System.out.println("Input the meal's name:");
    String name = scanner.nextLine();
    // Get ingredients
    System.out.println("Input the ingredients:");
    String ingredients = scanner.nextLine();

    // Print result
    Meal meal = new Meal(type, name, ingredients);
    System.out.println(meal);
    System.out.print("The meal has been added!");
  }
}