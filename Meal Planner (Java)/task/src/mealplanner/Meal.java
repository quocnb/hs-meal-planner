package mealplanner;

import java.util.Arrays;
import java.util.List;

enum MealType {
    BREAKFAST, LUNCH, DINNER
}
public class Meal {
    MealType type;
    String name;
    List<String> ingredients;
    final static String ONLY_LETTER_REGEX = "[a-zA-Z ]+";

    public Meal(String type, String name, String ingredients) {
        this.type = MealType.valueOf(type.toUpperCase());
        this.name = name;
        this.ingredients = Arrays.stream(ingredients.split(",")).map(String::trim).toList();
    }

    @Override
    public String toString() {
        return String.format("""
                Category: %s
                Name: %s
                Ingredients:
                %s""",
                type.name().toLowerCase(),
                name,
                String.join("\n", ingredients));
    }

    /**
     * Validate meal type.
     * Meal type must be breakfast, lunch or dinner.
     * @param type string
     * @return true: valid type otherwise false
     */
    public static boolean validateType(String type) {
        try {
            MealType.valueOf(type.toUpperCase());
            return true;
        } catch (Exception e) {
            return false;
        }
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
