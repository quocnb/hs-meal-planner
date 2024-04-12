import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealPlannerTests extends StageTest {

  @DynamicTest
  CheckResult normalExe1Test() {
    CheckOutput co = new CheckOutput();
    if (!co.start("Which meal do you want to add (breakfast, lunch, dinner)?") )
      return CheckResult.wrong("Your program should ask the user about meal category: \"(breakfast, lunch, dinner)?\"");

    if (!co.input("lunch", "Input the meal's name:"))
      return CheckResult.wrong("Your output should contain \"Input the meal's name:\"");

    if (!co.input("sushi", "Input the ingredients:"))
      return CheckResult.wrong("Your output should contain \"Input the ingredients:\"");

    if (!co.input("salmon, rice, avocado", "Category: lunch"))
      return CheckResult.wrong("Your output should contain \"Category: lunch\"");

    if (!co.inputNext("Name: sushi"))
      return CheckResult.wrong("Your output should contain \"Name: sushi\"");

    if (!co.inputNext("Ingredients:"))
      return CheckResult.wrong("Your output should contain \"Ingredients:\"");

    if (!co.inputNext("salmon", "rice", "avocado"))
      return CheckResult.wrong("Your output should contain the ingredients list");

    if (!co.inputNext("The meal has been added!"))
      return CheckResult.wrong("Your output should contain \"The meal has been added!\"");

    if (!co.programIsFinished() )
      return CheckResult.wrong("The application didn't exit.");

    return CheckResult.correct();
  }

  @DynamicTest
  CheckResult normalExe2Test() {
    CheckOutput co = new CheckOutput();
    if (!co.start("Which meal do you want to add (breakfast, lunch, dinner)?") )
      return CheckResult.wrong("Your program should ask the user about meal category: \"(breakfast, lunch, dinner)?\"");

    if (!co.input("dinner", "Input the meal's name:"))
      return CheckResult.wrong("Your output should contain \"Input the meal's name:\"");

    if (!co.input("salad", "Input the ingredients:"))
      return CheckResult.wrong("Your output should contain \"Input the ingredients:\"");

    if (!co.input("lettuce,tomato,onion,cheese,olives", "Category: dinner"))
      return CheckResult.wrong("Your output should contain \"Category: dinner\"");

    if (!co.inputNext("Name: salad"))
      return CheckResult.wrong("Your output should contain \"Name: salad\"");

    if (!co.inputNext("Ingredients:"))
      return CheckResult.wrong("Your output should contain \"Ingredients:\"");

    if (!co.inputNext("lettuce", "tomato", "onion", "cheese", "olives"))
      return CheckResult.wrong("Your output should contain the ingredients list");

    if (!co.inputNext("The meal has been added!"))
      return CheckResult.wrong("Your output should contain \"The meal has been added!\"");

    if (!co.programIsFinished() )
      return CheckResult.wrong("The application didn't exit.");

    return CheckResult.correct();
  }

  @DynamicTest
  CheckResult normalExe3Test() {
    CheckOutput co = new CheckOutput();
    if (!co.start("Which meal do you want to add (breakfast, lunch, dinner)?") )
      return CheckResult.wrong("Your program should ask the user about meal category: \"(breakfast, lunch, dinner)?\"");

    if (!co.input("breakfast", "Input the meal's name:"))
      return CheckResult.wrong("Your output should contain \"Input the meal's name:\"");

    if (!co.input("oatmeal", "Input the ingredients:"))
      return CheckResult.wrong("Your output should contain \"Input the ingredients:\"");

    if (!co.input("oats, milk, banana, peanut butter", "Category: breakfast"))
      return CheckResult.wrong("Your output should contain \"Category: breakfast\"");

    if (!co.inputNext("Name: oatmeal"))
      return CheckResult.wrong("Your output should contain \"Name: oatmeal\"");

    if (!co.inputNext("Ingredients:"))
      return CheckResult.wrong("Your output should contain \"Ingredients:\"");

    if (!co.inputNext("oats", "milk", "banana", "peanut butter"))
      return CheckResult.wrong("Your output should contain the ingredients list");

    if (!co.inputNext("The meal has been added!"))
      return CheckResult.wrong("Your output should contain \"The meal has been added!\"");

    if (!co.programIsFinished() )
      return CheckResult.wrong("The application didn't exit.");

    return CheckResult.correct();
  }

}

class CheckOutput {
  private TestedProgram main = new TestedProgram();
  private int position = 0;
  private boolean caseInsensitive = true;
  private boolean trimOutput = true;
  private List<String> arguments = new ArrayList<>();
  private boolean isStarted = false;
  private String lastOutput = "";

  private boolean checkOutput(String outputString, String... checkStr) {
    int searchPosition = position;
    for (String cStr : checkStr) {
      String str = caseInsensitive ? cStr.toLowerCase() : cStr;
      int findPosition = outputString.indexOf(str, searchPosition);
      if (findPosition == -1) return false;
      if (!outputString.substring(searchPosition, findPosition).isBlank()) return false;
      searchPosition = findPosition + str.length();
    }
    position = searchPosition;
    return true;
  }

  public boolean start(String... checkStr) {
    if (isStarted)
      return false;
    var outputString = main.start(arguments.toArray(new String[]{}));
    lastOutput = outputString;
    if (trimOutput) outputString = outputString.trim();
    if (caseInsensitive) outputString = outputString.toLowerCase();
    isStarted = true;
    return checkOutput(outputString, checkStr);
  }

  public void stop() {
    main.stop();
  }

  public boolean input(String input, String... checkStr) {
    if (main.isFinished()) return false;
    String outputString = main.execute(input);
    lastOutput = outputString;
    if (trimOutput) outputString = outputString.trim();
    if (caseInsensitive) outputString = outputString.toLowerCase();
    position = 0;
    return checkOutput(outputString, checkStr);
  }

  public boolean inputNext(String... checkStr) {
    String outputString = lastOutput;
    if (trimOutput) outputString = outputString.trim();
    if (caseInsensitive) outputString = outputString.toLowerCase();
    return checkOutput(outputString, checkStr);
  }

  public String getNextOutput(String input) {
    if (main.isFinished()) return "";
    String outputString = main.execute(input);
    lastOutput = outputString;
    position = 0;
    return outputString;
  }

  public String getLastOutput() {
    return lastOutput;
  }

  public boolean programIsFinished() {
    return main.isFinished();
  }

  public void setArguments(String... arguments) {
    this.arguments = Arrays.stream(arguments).toList();
  }

  public void setCaseInsensitive(boolean caseInsensitive) {
    this.caseInsensitive = caseInsensitive;
  }

  public void setTrimOutput(boolean trimOutput) {
    this.trimOutput = trimOutput;
  }
}