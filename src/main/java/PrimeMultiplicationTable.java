import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author John Gillson
 * @since 03/2019
 */
class PrimeMultiplicationTable {

  public static void main(String[] args) {
    int num;
    if (args == null || args.length == 0) {
      num = 10;
    } else {
      num = Integer.valueOf(args[0]);
    }

    if (num >= 0) {
      List<Integer> listOfPrimeNumbers = listOfPrimeNumbers(num);
      String[][] tableFirstRowAndColumn = createTableFirstRowAndColumn(listOfPrimeNumbers);
      String[][] multiplicationTable = createMultiplicationTable(tableFirstRowAndColumn);
      System.out.println(printMultiplicationTable(multiplicationTable));
    } else {
      System.out.println("Invalid integer - please enter a positive integer");
      System.exit(1);
    }
  }

  static List<Integer> listOfPrimeNumbers(int num) {
    List<Integer> listOfPrimes = new ArrayList<>();
    if (num == 0) {
      return listOfPrimes;
    }

    listOfPrimes.add(2);
    int i = 3;
    while (listOfPrimes.size() < num) {
      if (isPrimeNumber(i)) {
        listOfPrimes.add(i);
      }
      i += 2;
    }
    return listOfPrimes;
  }

  static boolean isPrimeNumber(int num) {
    if (num <= 1) return false;

    for (int x = 2; x * x <= num; x++) {
      if (num % x == 0) {
        return false;
      }
    }
    return true;
  }

  static String[][] createTableFirstRowAndColumn(List<Integer> listOfPrimeNumbers) {
    String[][] matrix = new String[0][0];
    if (CollectionUtils.isEmpty(listOfPrimeNumbers)) {
      return matrix;
    }

    int tableCapacity = listOfPrimeNumbers.size() + 1;
    int size = listOfPrimeNumbers.size();
    int i;
    matrix = new String[tableCapacity][tableCapacity];
    matrix[0][0] = "-";

    for (i = 0; i < size; i++) {
      matrix[0][i + 1] = String.valueOf(listOfPrimeNumbers.get(i));
    }
    for (i = 0; i < size; i++) {
      matrix[i + 1][0] = String.valueOf(listOfPrimeNumbers.get(i));
    }
    return matrix;
  }

  static String[][] createMultiplicationTable(String[][] matrix) {
    for (int i = 1; i < matrix.length; i++) {
      for (int j = 1; j < matrix[0].length; j++) {
        int product = Integer.valueOf(matrix[0][j]) * Integer.valueOf(matrix[i][0]);
        matrix[i][j] = String.valueOf(product);
      }
    }
    return matrix;
  }

  static String printMultiplicationTable(String[][] multiplicationTable) {
    if (multiplicationTable.length == 0) return "-";

    int capacity = multiplicationTable.length - 1;
    int lastRow = Integer.valueOf(multiplicationTable[capacity][capacity]);
    int largestNum = String.valueOf(lastRow).length();
    StringBuilder sb = new StringBuilder();

    for (String[] matrix : multiplicationTable) {
      for (String currNum : matrix) {
        String padding = IntStream.range(0, (largestNum - currNum.length()))
          .mapToObj(str -> " ").collect(Collectors.joining(""));
        sb.append(currNum).append(padding).append(" ");
      }
      sb.append('\n');
    }
    return sb.toString();
  }
}
