import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  private enum PossibleCards { A, K, Q, J, T }
  private enum PossibleTestCases{ test1, test2, test3, test4, test5 }
  private enum PossibleTestResponse {
    OK("OK"),
    Wrong("Wrong answer"),
    TimeLimit("Time limit exceeded"),
    RuntimeError("Runtime error");

    PossibleTestResponse(final String s) {
    }
  }


  public static void main(String[] args) {

    /*1º. Exercicio*/
    int plays = play("23A84Q", "K2Q25J");
    System.out.println(plays);

    /*2º. Exercicio - Este não foi entregue*/
//    String[] T = new String[]{"test1a", "test2","test1b","test1c","test3"};
//    String[] R = new String[]{"Wrong answer","OK","Runtime error","OK","Time limit exceeded"};
//    int success =  solution(T, R);
//    System.out.println(success);

  }

  private static int solution(final String[] T, final String[] R) {


    List<String> testCases = Arrays.asList(T);
    List<String> testResult = Arrays.asList(R);

    int numberOfGroups = grupCount(testCases);
    System.out.println(numberOfGroups);

    int countPassedTests = passedTestsCount(testCases, testResult);
    System.out.println(countPassedTests);


    return  countPassedTests * 100/numberOfGroups;
  }

  private static int passedTestsCount(List<String> testCases, List<String> testResult) {
    int countPassedTests= 0;
    while  (testCases.size() != 0){
      final String tCase = testCases.get(0);

      testCases = testCases.subList(1, testCases.size());

      final String tResult = testResult.get(0);
      testResult = testResult.subList(1, testResult.size());

      PossibleTestCases testCase = null;
      PossibleTestResponse testResponse = null;

      try {
        testCase = PossibleTestCases.valueOf(tCase);
      } catch(Exception e) {
        //TODO: This is a quick parsing to avoid extra validations ( not the best solution of course)
      }

      try {
        testResponse = PossibleTestResponse.valueOf(tResult);
      } catch(Exception e) {
        //TODO: This is a quick parsing to avoid extra validations ( not the best solution of course)
      }

      if(testCase != null && testResponse != null ){
        if(testResponse.toString().equals("OK")) {
          countPassedTests ++;
        }

      }

    }

    return countPassedTests;
  }

  private static int grupCount(final List<String> testCases) {

    Map<String,Integer> tesAndCount = new HashMap();
    for (String test: testCases) {
      String firstFiveChars = "";
      if (test.length() > 4) {
        firstFiveChars = test.substring(0, 5);
      }
      Integer count = tesAndCount.get(firstFiveChars);

      if(count == null) {
        tesAndCount.put(firstFiveChars,1);
      }else {
        tesAndCount.put(firstFiveChars, ++count);
      }
    }
    return tesAndCount.size();
  }

  private static int play(String A, String B) {
    int countAlecWins = 0;


    //Com listas é mais rapido de trabalhar
    List<String> playerACards = Arrays.asList(A.split(""));
    List<String> playerBCards = Arrays.asList(B.split(""));

    while(playerACards.size() != 0) {
      final String cardA = playerACards.get(0);
      playerACards = playerACards.subList(1, playerACards.size());

      final String cardB = playerBCards.get(0);
      playerBCards = playerBCards.subList(1, playerBCards.size());

      PossibleCards cardAEnumVal = null;
      PossibleCards cardBEnumVal = null;

      try {
        cardAEnumVal = PossibleCards.valueOf(cardA);
      } catch(Exception e) {
        //TODO: This is a quick parsing to avoid extra validations ( not the best solution of course)
      } try {
        cardBEnumVal = PossibleCards.valueOf(cardB);
      } catch(Exception e) {
        //TODO: This is a quick parsing to avoid extra validations ( not the best solution of course)
      }

      if(cardAEnumVal != null && cardBEnumVal != null) {
        if(cardAEnumVal.ordinal() < cardBEnumVal.ordinal()) {
          countAlecWins++;
        }

      } else if(cardAEnumVal != null && cardBEnumVal == null) {
        countAlecWins++;
      } else if(cardAEnumVal == null && cardBEnumVal != null) {

      } else {
        if(Integer.valueOf(cardA) > Integer.valueOf(cardB)) {
          countAlecWins++;
        }
      }
    }
    return countAlecWins;
  }
}
