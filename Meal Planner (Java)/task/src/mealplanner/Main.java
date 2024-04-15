package mealplanner;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
  static List<Meal> meals;
  static Scanner scanner;
  static DbManager dbManager;
  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    dbManager = new DbManager();
    meals = dbManager.getMeals();

    while (true) {
        System.out.println("What would you like to do (add, show, plan, exit)?");
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
          case "plan":
            makePlan();
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
      List<Meal> mealsInCategory = dbManager.getMeal(category, false);
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

  static void makePlan() {
    String[] categories = {"breakfast", "lunch", "dinner"};
    List<Plan> plans = new ArrayList<>();
    for (Plan.MealOption day : Plan.MealOption.values()) {
      System.out.println(day.name());
      for (Plan.MealCategory category : Plan.MealCategory.values()) {
        Plan plan = new Plan();
        plan.mealOption = day;
        plan.mealCategory = category;
        List<Meal> mealsInCategory = dbManager.getMeal(category.name(), true);
        mealsInCategory.forEach(s -> System.out.println(s.meal));
        System.out.printf("Choose the %s for %s from the list above:\n", category, day.name());
        while (true) {
          String mealName = scanner.nextLine();
          var selectedMeal = mealsInCategory.stream().filter(s -> s.meal.equals(mealName)).findFirst();
          if (selectedMeal.isEmpty()) {
            System.out.println("This meal doesn’t exist. Choose a meal from the list above.");
            continue;
          }
          plan.meal = selectedMeal.get();
          break;
        }
        plans.add(plan);
      }
      System.out.printf("Yeah! We planned the meals for %s.\n", day.name());
    }
    dbManager.savePlan(plans);
    System.out.println();

    var planMap = plans.stream().collect(Collectors.groupingBy(s -> s.mealOption));
    for (Plan.MealOption mealOption : Plan.MealOption.values()) {
      var planList = planMap.get(mealOption).stream().sorted().toList();
      System.out.printf("""
            %s
            Breakfast: %s
            Lunch: %s
            Dinner: %s

            """, mealOption.name(), planList.get(0).meal.meal, planList.get(1).meal.meal, planList.get(2).meal.meal);
    };
  }
}