package mealplanner;

public class Queries {
    public static String CREATE_TBL_MEALS = """
            CREATE TABLE IF NOT EXISTS meals (
              meal_id INT PRIMARY KEY,
              category VARCHAR(255),
              meal VARCHAR(255)
            );""";
    public static String CREATE_TBL_INGREDIENTS = """
            CREATE TABLE IF NOT EXISTS ingredients (
              ingredient_id INT,
              ingredient VARCHAR(255),
              meal_id INT
            );""";
    public static String SELECT_ALL_MEALS = "SELECT * FROM meals ORDER BY meal_id;";
    public static String SELECT_INGREDIENTS_BY_MEAL = "SELECT * FROM ingredients WHERE meal_id = ?;";

    public static String INSERT_MEAL = "INSERT INTO meals(meal_id, category, meal) VALUES (?, ?, ?);";
    public static String INSERT_INGREDIENTS = "INSERT INTO ingredients(ingredient_id, ingredient, meal_id) VALUES (0, ?, ?);";
}
