package mealplanner;

enum MealType {
    BREAKFAST, LUNCH, DINNER
}
public class Meal {
    MealType type;
    String name;
    String[] ingredients;

    public Meal(String type, String name, String ingredients) {
        this.type = MealType.valueOf(type.toUpperCase());
        this.name = name;
        this.ingredients = ingredients.split(",");
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
}
