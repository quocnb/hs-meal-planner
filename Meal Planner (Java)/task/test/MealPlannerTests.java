import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealPlannerTests extends StageTest {

  @DynamicTest
  CheckResult normalExe4Test() {
    CheckOutput co = new CheckOutput();
    if (!co.start("What would you like to do (add, show, exit)?"))
      return CheckResult.wrong("Your program should ask the user about the required action: \"(add, show, exit)?\"");

    if (!co.input("add", "Which meal do you want to add (breakfast, lunch, dinner)?"))
      return CheckResult.wrong("Your program should ask the user about meal category: \"(breakfast, lunch, dinner)?\"");

    if (!co.input("lunch", "Input the meal's name:"))
      return CheckResult.wrong("Your output should contain \"Input the meal's name:\"");

    if (!co.input("sushi", "Input the ingredients:"))
      return CheckResult.wrong("Your output should contain \"Input the ingredients:\"");

    if (!co.input("salmon, rice, avocado", "The meal has been added!"))
      return CheckResult.wrong("Your output should contain \"The meal has been added!\"");

    if (!co.inputNext("What would you like to do (add, show, exit)?"))
      return CheckResult.wrong("Your program should ask the user about the required action: \"(add, show, exit)?\"");

    if (!co.input("show", "Category: lunch", "Name: sushi", "Ingredients:",
            "salmon", "rice", "avocado"))
      return CheckResult.wrong("Wrong \"show\" output for a saved meal.");

    if (!co.input("exit", "Bye!"))
      return CheckResult.wrong("Your output should contain \"Bye!\"");

    if (!co.programIsFinished())
      return CheckResult.wrong("The application didn't exit.");

    return CheckResult.correct();
  }

  @DynamicTest
  CheckResult normalExe5Test() {
    CheckOutput co = new CheckOutput();
    if (!co.start("What would you like to do (add, show, exit)?"))
      return CheckResult.wrong("Your program should ask the user about the required action: \"(add, show, exit)?\"");

    if (!co.input("add", "Which meal do you want to add (breakfast, lunch, dinner)?"))
      return CheckResult.wrong("Your program should ask the user about meal category: \"(breakfast, lunch, dinner)?\"");

    if (!co.input("lunch", "Input the meal's name:"))
      return CheckResult.wrong("Your output should contain \"Input the meal's name:\"");

    if (!co.input("sushi", "Input the ingredients:"))
      return CheckResult.wrong("Your output should contain \"Input the ingredients:\"");

    if (!co.input("salmon, rice, avocado", "The meal has been added!"))
      return CheckResult.wrong("Your output should contain \"The meal has been added!\"");

    if (!co.inputNext("What would you like to do (add, show, exit)?"))
      return CheckResult.wrong("Your program should ask the user about the required action: \"(add, show, exit)?\"");

    if (!co.input("add", "Which meal do you want to add (breakfast, lunch, dinner)?"))
      return CheckResult.wrong("Your program should ask the user about meal category: \"(breakfast, lunch, dinner)?\"");

    if (!co.input("breakfast", "Input the meal's name:"))
      return CheckResult.wrong("Your output should contain \"Input the meal's name:\"");

    if (!co.input("banana oatmeal", "Input the ingredients:"))
      return CheckResult.wrong("Your output should contain \"Input the ingredients:\"");

    if (!co.input("oats, milk, banana, peanut butter", "The meal has been added!"))
      return CheckResult.wrong("Your output should contain \"The meal has been added!\"");

    if (!co.inputNext("What would you like to do (add, show, exit)?"))
      return CheckResult.wrong("Your program should ask the user about the required action: \"(add, show, exit)?\"");

    if (!co.input("show", "Category: lunch", "Name: sushi", "Ingredients:",
            "salmon", "rice", "avocado", "Category: breakfast", "Name: banana oatmeal", "Ingredients:",
            "oats", "milk", "banana", "peanut butter"))
      return CheckResult.wrong("Wrong \"show\" output for 2 saved meals.");

    if (!co.input("exit", "Bye!"))
      return CheckResult.wrong("Your output should contain \"Bye!\"");

    if (!co.programIsFinished())
      return CheckResult.wrong("The application didn't exit.");

    return CheckResult.correct();
  }

  @DynamicTest
  CheckResult exeWithErrors1Test() {
    CheckOutput co = new CheckOutput();
    if (!co.start("What would you like to do (add, show, exit)?"))
      return CheckResult.wrong("Your program should ask the user about the required action: \"(add, show, exit)?\"");

    if (!co.input("show", "No meals saved. Add a meal first."))
      return CheckResult.wrong("Your output should contain \"No meals saved. Add a meal first.\"");

    if (!co.input("new meal", "What would you like to do (add, show, exit)?"))
      return CheckResult.wrong("Your program should ask the user about the required action after a wrong command " +
              "input.");

    if (!co.input("meal", "What would you like to do (add, show, exit)?"))
      return CheckResult.wrong("Your program should ask the user about the required action after a wrong command " +
              "input.");

    if (!co.input("", "What would you like to do (add, show, exit)?"))
      return CheckResult.wrong("Your program should ask the user about the required action after a wrong command " +
              "input.");

    if (!co.input(" \t", "What would you like to do (add, show, exit)?"))
      return CheckResult.wrong("Your program should ask the user about the required action after a wrong command " +
              "input.");

    if (!co.input("add", "Which meal do you want to add (breakfast, lunch, dinner)?"))
      return CheckResult.wrong("Your program should ask the user about meal category: \"(breakfast, lunch, dinner)?\"");

    if (!co.input("dessert", "Wrong meal category! Choose from: breakfast, lunch, dinner."))
      return CheckResult.wrong("Your output should contain \"Wrong meal category! Choose from: breakfast, lunch, " +
              "dinner.\"");

    if (!co.input("nothing", "Wrong meal category! Choose from: breakfast, lunch, dinner."))
      return CheckResult.wrong("Your output should contain \"Wrong meal category! Choose from: breakfast, lunch, " +
              "dinner.\"");

    if (!co.input("meal1", "Wrong meal category! Choose from: breakfast, lunch, dinner."))
      return CheckResult.wrong("Your output should contain \"Wrong meal category! Choose from: breakfast, lunch, " +
              "dinner.\"");

    if (!co.input("meal@", "Wrong meal category! Choose from: breakfast, lunch, dinner."))
      return CheckResult.wrong("Your output should contain \"Wrong meal category! Choose from: breakfast, lunch, " +
              "dinner.\"");

    if (!co.input("", "Wrong meal category! Choose from: breakfast, lunch, dinner."))
      return CheckResult.wrong("Your output should contain \"Wrong meal category! Choose from: breakfast, lunch, " +
              "dinner.\"");

    if (!co.input(" \t", "Wrong meal category! Choose from: breakfast, lunch, dinner."))
      return CheckResult.wrong("Your output should contain \"Wrong meal category! Choose from: breakfast, lunch, " +
              "dinner.\"");

    if (!co.input("lunch", "Input the meal's name:"))
      return CheckResult.wrong("Your output should contain \"Input the meal's name:\"");

    if (!co.input("burger1", "Wrong format. Use letters only!"))
      return CheckResult.wrong("Your output should contain \"Wrong format. Use letters only!\"");

    if (!co.input("sushi@", "Wrong format. Use letters only!"))
      return CheckResult.wrong("Your output should contain \"Wrong format. Use letters only!\"");

    if (!co.input("", "Wrong format. Use letters only!"))
      return CheckResult.wrong("Your output should contain \"Wrong format. Use letters only!\"");

    if (!co.input(" \t", "Wrong format. Use letters only!"))
      return CheckResult.wrong("Your output should contain \"Wrong format. Use letters only!\"");

    if (!co.input("sushi", "Input the ingredients:"))
      return CheckResult.wrong("Your output should contain \"Input the ingredients:\"");

    if (!co.input("salmon, rice1, avocado", "Wrong format. Use letters only!"))
      return CheckResult.wrong("Your output should contain \"Wrong format. Use letters only!\"");

    if (!co.input("salmon, , avocado", "Wrong format. Use letters only!"))
      return CheckResult.wrong("Your output should contain \"Wrong format. Use letters only!\"");

    if (!co.input("salmon,, avocado", "Wrong format. Use letters only!"))
      return CheckResult.wrong("Your output should contain \"Wrong format. Use letters only!\"");

    if (!co.input("salmon, rice, ", "Wrong format. Use letters only!"))
      return CheckResult.wrong("Your output should contain \"Wrong format. Use letters only!\"");

    if (!co.input("salmon, rice, avocado@", "Wrong format. Use letters only!"))
      return CheckResult.wrong("Your output should contain \"Wrong format. Use letters only!\"");

    if (!co.input("salmon, rice, avocado", "The meal has been added!"))
      return CheckResult.wrong("Your output should contain \"The meal has been added!\"");

    if (!co.inputNext("What would you like to do (add, show, exit)?"))
      return CheckResult.wrong("Your program should ask the user about the required action: \"(add, show, exit)?\"");

    if (!co.input("add", "Which meal do you want to add (breakfast, lunch, dinner)?"))
      return CheckResult.wrong("Your program should ask the user about meal category: \"(breakfast, lunch, dinner)?\"");

    if (!co.input("breakfast", "Input the meal's name:"))
      return CheckResult.wrong("Your output should contain \"Input the meal's name:\"");

    if (!co.input("banana oatmeal", "Input the ingredients:"))
      return CheckResult.wrong("Your output should contain \"Input the ingredients:\"");

    if (!co.input("oats, milk, banana, peanut butter", "The meal has been added!"))
      return CheckResult.wrong("Your output should contain \"The meal has been added!\"");

    if (!co.inputNext("What would you like to do (add, show, exit)?"))
      return CheckResult.wrong("Your program should ask the user about the required action: \"(add, show, exit)?\"");

    if (!co.input("show", "Category: lunch", "Name: sushi", "Ingredients:",
            "salmon", "rice", "avocado", "Category: breakfast", "Name: banana oatmeal", "Ingredients:",
            "oats", "milk", "banana", "peanut butter"))
      return CheckResult.wrong("Wrong \"show\" output for 2 saved meals.");

    if (!co.input("exit", "Bye!"))
      return CheckResult.wrong("Your output should contain \"Category: lunch\"");

    if (!co.programIsFinished())
      return CheckResult.wrong("Bye!");

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