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
            statement.executeUpdate(Queries.CREATE_TBL_PLANS);
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
            meals = getMealFromResultSet(mealRS);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return meals;
    }

    public List<Meal> getMeal(String category, boolean orderByName) {
        List<Meal> meals = new ArrayList<>();
        try {
            String sql = orderByName ? Queries.SELECT_MEAL_BY_CATEGORY_ORDER_BY_NAME : Queries.SELECT_MEAL_BY_CATEGORY;
            PreparedStatement getMealStm = connection.prepareStatement(sql);
            getMealStm.setString(1, category);
            ResultSet mealRS = getMealStm.executeQuery();
            meals = getMealFromResultSet(mealRS);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return meals;
    }

    List<Meal> getMealFromResultSet(ResultSet rs) throws SQLException {
        List<Meal> result = new ArrayList<>();
        while (rs.next()) {
            int mealId = rs.getInt("meal_id");
            String category = rs.getString("category");
            String name = rs.getString("meal");
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
            result.add(meal);
        }
        return result;
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

    public List<Plan> getAllPlans() {
        List<Plan> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(Queries.SELECT_ALL_PLANS);
            result = getPlans(rs);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    private List<Plan> getPlans(ResultSet rs) throws SQLException {
        List<Plan> result = new ArrayList<>();
        while (rs.next()) {
            int mealOption = rs.getInt("meal_option");
            int categoryId = rs.getInt("meal_category");
            int mealId = rs.getInt("meal_id");
            PreparedStatement getIngredientsStm = connection.prepareStatement(Queries.SELECT_INGREDIENTS_BY_MEAL);
            getIngredientsStm.setInt(1, mealId);
            ResultSet ingredientsRs = getIngredientsStm.executeQuery();
            List<Ingredient> ingredients = new ArrayList<>();
            while (ingredientsRs.next()) {
                int ingredientId = ingredientsRs.getInt("ingredient_id");
                String ingredient = ingredientsRs.getString("ingredient");
                ingredients.add(new Ingredient(ingredientId, ingredient, mealId));
            }
            Meal meal = new Meal(mealId, Plan.MealCategory.values()[categoryId - 1].name(), "", ingredients);
            Plan plan = new Plan(mealOption, categoryId, meal);
            result.add(plan);
        }
        return result;
    }

    public void savePlan(List<Plan> plans) {
        try {
            PreparedStatement statement = connection.prepareStatement(Queries.INSERT_PLAN);
            for (Plan plan : plans) {
                statement.setInt(1, plan.mealOption.mealOptionId);
                statement.setInt(2, plan.mealCategory.mealCategoryId);
                statement.setInt(3, plan.meal.mealId);
                statement.execute();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
