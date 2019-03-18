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

  /**
   * This method is the main entry point of the program.
   *
   * @param args the args passed in, if any
   */
  public static void main(String[] args) {
    int n;
    if (args == null || args.length == 0) {
      n = 10;
    } else {
      n = Integer.valueOf(args[0]);
    }

    if (n >= 0) {
      List<Integer> listOfPrimeNumbers = listOfPrimeNumbers(n);
      String[][] tableFirstRowAndColumn = createTableFirstRowAndColumn(listOfPrimeNumbers);
      String[][] multiplicationTable = createMultiplicationTable(tableFirstRowAndColumn);
      System.out.println(printMultiplicationTable(multiplicationTable));
    } else {
      System.out.println("Invalid integer - please enter a positive integer");
      System.exit(1);
    }
  }

  /**
   * This method returns the List of Integers of `n` prime numbers, adding a number to the list if it's prime.
   * The prime number of 2 is added to the list, and testing starts at index 3.
   * The index is then incremented by 2 so the candidate numbers are always odd.
   *
   * The time complexity of this method is `O(n)` as the growth order is linear as the size of `n` increases.
   *
   * @param n the number of prime numbers
   * @return List of Integers of `n` prime numbers
   */
  static List<Integer> listOfPrimeNumbers(int n) {
    List<Integer> listOfPrimes = new ArrayList<>();
    if (n == 0) {
      return listOfPrimes;
    }

    listOfPrimes.add(2);
    int i = 3;
    while (listOfPrimes.size() < n) {
      if (isPrimeNumber(i)) {
        listOfPrimes.add(i);
      }
      i += 2;
    }
    return listOfPrimes;
  }

  /**
   * This method checks if a number is prime by checking for divisibility on numbers less than it.
   * It only needs to go up to the square root of `n` because if `n` is divisible by a number greater than its square root,
   * then it's divisible by something smaller than it.
   * The work inside the for loop is constant.
   * Therefore we just need to know how many iterations the for loop goes through in the worst case.
   *
   * The time complexity of this method is `O(sqrt(n))` as the for loop will start when x = 2 and end when x * x <= n.
   * Or, in other words, it stops when x = sqrt(n).
   *
   * @param n the prime number to check
   * @return true if the number is prime, false otherwise
   */
  static boolean isPrimeNumber(int n) {
    if (n <= 1) return false;

    for (int x = 2; x * x <= n; x++) {
      if (n % x == 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Creates the initial table with the first row and column represented by the passed in list of prime numbers.
   * A 2D array is used here to create the initial row (first array in the 2D array) and then the columns are
   * generated where each prime number is the first element in its own array.
   *
   * The time complexity of this method is `O(2n) -> O(n)` as the growth order is linear and iterates twice over
   * the list of prime numbers.
   *
   * Example:
   * -   2   3   5   7   11  13  17  19  23  29
   * 2
   * 3
   * 5
   * 7
   * 11
   * 13
   * 17
   * 19
   * 23
   * 29
   *
   * @param listOfPrimeNumbers the list of prime numbers
   * @return the initial table with the first row and column as a 2D array
   */
  static String[][] createTableFirstRowAndColumn(List<Integer> listOfPrimeNumbers) {
    String[][] table = new String[0][0];
    if (CollectionUtils.isEmpty(listOfPrimeNumbers)) {
      return table;
    }

    int tableCapacity = listOfPrimeNumbers.size() + 1;
    int size = listOfPrimeNumbers.size();
    int i;
    table = new String[tableCapacity][tableCapacity];
    table[0][0] = "-";

    for (i = 0; i < size; i++) {
      table[0][i + 1] = String.valueOf(listOfPrimeNumbers.get(i));
    }
    for (i = 0; i < size; i++) {
      table[i + 1][0] = String.valueOf(listOfPrimeNumbers.get(i));
    }
    return table;
  }

  /**
   * Creates the prime number multiplication table as a 2D array with the products of `n` prime numbers.
   *
   * The time complexity of this method is `O(n<sup>2</sup>)` due to the nested for loops to create the
   * prime number multiplication table with each product.
   *
   * Example:
   * -   2   3   5   7   11  13  17  19  23  29
   * 2   4   6   10  14  22  26  34  38  46  58
   * 3   6   9   15  21  33  39  51  57  69  87
   * 5   10  15  25  35  55  65  85  95  115 145
   * 7   14  21  35  49  77  91  119 133 161 203
   * 11  22  33  55  77  121 143 187 209 253 319
   * 13  26  39  65  91  143 169 221 247 299 377
   * 17  34  51  85  119 187 221 289 323 391 493
   * 19  38  57  95  133 209 247 323 361 437 551
   * 23  46  69  115 161 253 299 391 437 529 667
   * 29  58  87  145 203 319 377 493 551 667 841
   *
   * @param table the initial table with the first row and column
   * @return the prime number multiplication table as a 2D array
   */
  static String[][] createMultiplicationTable(String[][] table) {
    for (int i = 1; i < table.length; i++) {
      for (int j = 1; j < table[0].length; j++) {
        int product = Integer.valueOf(table[0][j]) * Integer.valueOf(table[i][0]);
        table[i][j] = String.valueOf(product);
      }
    }
    return table;
  }

  /**
   * Returns the prime number multiplication table as a String and prints this to the STDOUT.
   *
   * The time complexity of this method is `O(n<sup>2</sup>)` due to the nested for loops to
   * print the prime number multiplication table.
   *
   * @param multiplicationTable the prime number multiplication table
   * @return the string representation of the prime number multiplication table
   */
  static String printMultiplicationTable(String[][] multiplicationTable) {
    if (multiplicationTable.length == 0) return "-";

    int capacity = multiplicationTable.length - 1;
    int lastRow = Integer.valueOf(multiplicationTable[capacity][capacity]);
    int largestNum = String.valueOf(lastRow).length();
    StringBuilder sb = new StringBuilder();

    for (String[] table : multiplicationTable) {
      for (String currNum : table) {
        String padding = IntStream.range(0, (largestNum - currNum.length()))
          .mapToObj(str -> " ").collect(Collectors.joining(""));
        sb.append(currNum).append(padding).append(" ");
      }
      sb.append('\n');
    }
    return sb.toString();
  }
}
