package mealplanner;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    int mealId;
    String category;
    String meal;
    List<Ingredient> ingredients;
    final static String ONLY_LETTER_REGEX = "[a-zA-Z ]+";

    public Meal(int mealId, String category, String meal, String ingredients) {
        this.mealId = mealId;
        this.category = category;
        this.meal = meal;
        this.ingredients = new ArrayList<>();
        for (String ingredient : ingredients.split(",")) {
            this.ingredients.add(new Ingredient(ingredient.trim()));
        }
    }

    public Meal(int mealId, String category, String meal, List<Ingredient> ingredients) {
        this.mealId = mealId;
        this.category = category;
        this.meal = meal;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return String.format("""
                Name: %s
                Ingredients:
                %s""",
                meal,
                String.join("\n", ingredients.stream().map(Ingredient::toString).toList()));
    }

    /**
     * Validate meal type.
     * Meal type must be breakfast, lunch or dinner.
     * @param category string
     * @return true: valid type otherwise false
     */
    public static boolean validateCategory(String category) {
        final List<String> validCategories = List.of("breakfast", "lunch", "dinner");
        return validCategories.contains(category);
    }

    /**
     * Validate meal name.
     * Meal name must be letters only.
     * @param name string
     * @return true: valid name otherwise false
     */
    public static boolean validateName(String name) {
        return name.matches(ONLY_LETTER_REGEX);
    }

    /**
     * Validate ingredients.
     * Meal name must be letters only.
     * @param name string
     * @return true: valid name otherwise false
     */
    public static boolean validateIngredients(String ingredients) {
        if (ingredients.isEmpty()) {
            return false;
        }
        for (String ingredient : ingredients.split(",")) {
            if (ingredient.trim().isEmpty()) {
                return false;
            }
            if (!ingredient.matches(ONLY_LETTER_REGEX)) {
                return false;
            }
        }
        return true;
    }
}
