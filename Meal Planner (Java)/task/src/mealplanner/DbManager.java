package mealplanner;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {
    private Connection connection;
    public DbManager() {
        try {
            String DB_URL = "jdbc:postgresql:meals_db";
            String USER = "postgres";
            String PASS = "1111";

            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(true);
            migrate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void migrate() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(Queries.CREATE_TBL_MEALS);
            statement.executeUpdate(Queries.CREATE_TBL_INGREDIENTS);
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Meal> getMeals() {
        List<Meal> meals = new ArrayList<>();
        try {
            Statement getMealStm = connection.createStatement();
            ResultSet mealRS = getMealStm.executeQuery(Queries.SELECT_ALL_MEALS);
            while (mealRS.next()) {
                int mealId = mealRS.getInt("meal_id");
                String category = mealRS.getString("category");
                String name = mealRS.getString("meal");
                PreparedStatement getIngredientsStm = connection.prepareStatement(Queries.SELECT_INGREDIENTS_BY_MEAL);
                getIngredientsStm.setInt(1, mealId);
                ResultSet ingredientsRs = getIngredientsStm.executeQuery();
                List<Ingredient> ingredients = new ArrayList<>();
                while (ingredientsRs.next()) {
                    int ingredientId = ingredientsRs.getInt("ingredient_id");
                    String ingredient = ingredientsRs.getString("ingredient");
                    ingredients.add(new Ingredient(ingredientId, ingredient, mealId));
                }
                Meal meal = new Meal(mealId, category, name, ingredients);
                meals.add(meal);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return meals;
    }
    public void addMeal(Meal meal) {
        try {
            PreparedStatement statement = connection.prepareStatement(Queries.INSERT_MEAL);
            statement.setInt(1, meal.mealId);
            statement.setString(2, meal.category);
            statement.setString(3, meal.meal);
            statement.execute();
            statement = connection.prepareStatement(Queries.INSERT_INGREDIENTS);
            statement.setInt(2, meal.mealId);
            for (Ingredient ingredient : meal.ingredients) {
                statement.setString(1, ingredient.ingredient);
                statement.execute();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
