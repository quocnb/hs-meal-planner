package mealplanner;

public class Ingredient {
    int ingredientId;
    String ingredient;
    int mealId;

    public Ingredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Ingredient(int ingredientId, String ingredient, int mealId) {
        this.ingredientId = ingredientId;
        this.ingredient = ingredient;
        this.mealId = mealId;
    }

    @Override
    public String toString() {
        return ingredient;
    }
}
