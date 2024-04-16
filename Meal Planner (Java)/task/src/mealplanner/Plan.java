package mealplanner;

import java.time.DayOfWeek;

public class Plan implements Comparable<Plan> {
    enum MealOption {
        Monday(1), Tuesday(2), Wednesday(3), Thursday(4), Friday(5), Saturday(6), Sunday(7);

        final int mealOptionId;
        MealOption(int id) {
            mealOptionId = id;
        }
    }
    enum MealCategory {
        breakfast(1), lunch(2), dinner(3);

        final int mealCategoryId;
        MealCategory(int id) {
            mealCategoryId = id;
        }
    }

    MealOption mealOption;
    MealCategory mealCategory;
    Meal meal;

    public Plan() {}
    public Plan(int mealOption, int mealCategory, Meal meal) {
        this.mealOption = MealOption.values()[mealOption - 1];
        this.mealCategory = MealCategory.values()[mealCategory - 1];
        this.meal = meal;
    }

    @Override
    public int compareTo(Plan o) {
        return this.mealCategory.compareTo(o.mealCategory);
    }
}
