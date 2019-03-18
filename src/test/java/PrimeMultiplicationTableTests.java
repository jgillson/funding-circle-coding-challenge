import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author John Gillson
 * @since 03/2019
 */
class PrimeMultiplicationTableTests {

  @Test
  @DisplayName("Returns a list of 10 prime numbers when num is 10")
  void testListOfPrimes() {
    int num = 10;
    List<Integer> expectedListOfPrimeNumbers = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
    List<Integer> actualListOfPrimeNumbers = PrimeMultiplicationTable.listOfPrimeNumbers(num);

    assertAll(
      () -> assertThat(actualListOfPrimeNumbers, allOf(is(notNullValue()), is(not(empty())), hasSize(10))),
      () -> assertEquals(expectedListOfPrimeNumbers, actualListOfPrimeNumbers)
    );
  }

  @Test
  @DisplayName("Returns an empty list of prime numbers when num is 0")
  void testEmptyListOfPrimes() {
    int num = 0;
    List<Integer> expectedListOfPrimeNumbers = Collections.emptyList();
    List<Integer> actualListOfPrimeNumbers = PrimeMultiplicationTable.listOfPrimeNumbers(num);

    assertAll(
      () -> assertThat(actualListOfPrimeNumbers, allOf(is(notNullValue()), is(empty()))),
      () -> assertEquals(expectedListOfPrimeNumbers, actualListOfPrimeNumbers)
    );
  }

  @Test
  @DisplayName("Returns true for various valid prime numbers -> 2, 5, 7, 479001599")
  void testValidPrimes() {
    boolean isPrime_2 = PrimeMultiplicationTable.isPrimeNumber(2);
    boolean isPrime_5 = PrimeMultiplicationTable.isPrimeNumber(5);
    boolean isPrime_7 = PrimeMultiplicationTable.isPrimeNumber(7);
    boolean isPrime_479001599 = PrimeMultiplicationTable.isPrimeNumber(479001599);

    assertAll(
      () -> assertThat(isPrime_2, is(true)),
      () -> assertThat(isPrime_5, is(true)),
      () -> assertThat(isPrime_7, is(true)),
      () -> assertThat(isPrime_479001599, is(true))
    );
  }

  @Test
  @DisplayName("Returns false for various invalid prime numbers -> 1, 10, 20, 798562661")
  void testInvalidPrimes() {
    boolean isPrime_1 = PrimeMultiplicationTable.isPrimeNumber(1);
    boolean isPrime_10 = PrimeMultiplicationTable.isPrimeNumber(10);
    boolean isPrime_20 = PrimeMultiplicationTable.isPrimeNumber(20);
    boolean isPrime_798562661 = PrimeMultiplicationTable.isPrimeNumber(798562661);

    assertAll(
      () -> assertThat(isPrime_1, is(false)),
      () -> assertThat(isPrime_10, is(false)),
      () -> assertThat(isPrime_20, is(false)),
      () -> assertThat(isPrime_798562661, is(false))
    );
  }

  @Test
  @DisplayName("Creates table with expected first row and column when num is 10")
  void testCreateTableFirstRowAndColumnFor10PrimeNumbers() {
    int num = 10;
    List<Integer> listOfPrimeNumbers = PrimeMultiplicationTable.listOfPrimeNumbers(num);
    String[][] tableFirstRowAndColumn = PrimeMultiplicationTable.createTableFirstRowAndColumn(listOfPrimeNumbers);
    String[] expectedFirstRow = {"-", "2", "3", "5", "7", "11", "13", "17", "19", "23", "29"};

    assertAll(
      () -> assertThat(listOfPrimeNumbers, allOf(is(notNullValue()), is(not(empty())), hasSize(10))),
      () -> assertThat(tableFirstRowAndColumn, is(notNullValue())),
      () -> assertThat(tableFirstRowAndColumn.length, is(11)),
      () -> assertThat(tableFirstRowAndColumn[0], is(arrayWithSize(11))),
      () -> assertThat(Arrays.equals(expectedFirstRow, tableFirstRowAndColumn[0]), is(true))
    );
  }

  @Test
  @DisplayName("Creates table with expected first row and column when num is 20")
  void testCreateTableFirstRowAndColumnFor20PrimeNumbers() {
    int num = 20;
    List<Integer> listOfPrimeNumbers = PrimeMultiplicationTable.listOfPrimeNumbers(num);
    String[][] tableFirstRowAndColumn = PrimeMultiplicationTable.createTableFirstRowAndColumn(listOfPrimeNumbers);
    String[] expectedFirstRow = {"-", "2", "3", "5", "7", "11", "13", "17", "19", "23", "29", "31",
      "37", "41", "43", "47", "53", "59", "61", "67", "71"};

    assertAll(
      () -> assertThat(listOfPrimeNumbers, allOf(is(notNullValue()), is(not(empty())), hasSize(20))),
      () -> assertThat(tableFirstRowAndColumn, is(notNullValue())),
      () -> assertThat(tableFirstRowAndColumn.length, is(21)),
      () -> assertThat(tableFirstRowAndColumn[0], is(arrayWithSize(21))),
      () -> assertThat(Arrays.equals(expectedFirstRow, tableFirstRowAndColumn[0]), is(true))
    );
  }

  @Test
  @DisplayName("Creates table with expected first row and column when num is 0")
  void testCreateTableFirstRowAndColumnFor0PrimeNumbers() {
    int num = 0;
    List<Integer> listOfPrimeNumbers = PrimeMultiplicationTable.listOfPrimeNumbers(num);
    String[][] tableFirstRowAndColumn = PrimeMultiplicationTable.createTableFirstRowAndColumn(listOfPrimeNumbers);

    assertAll(
      () -> assertThat(listOfPrimeNumbers, allOf(is(notNullValue()), is(empty()))),
      () -> assertThat(tableFirstRowAndColumn, is(notNullValue())),
      () -> assertThat(tableFirstRowAndColumn.length, is(0))
    );
  }

  @Test
  @DisplayName("Creates multiplication table with expected first row and column when num is 10")
  void testCreateMultiplicationTableFor10PrimeNumbers() {
    int num = 10;
    List<Integer> listOfPrimeNumbers = PrimeMultiplicationTable.listOfPrimeNumbers(num);
    String[][] tableFirstRowAndColumn = PrimeMultiplicationTable.createTableFirstRowAndColumn(listOfPrimeNumbers);
    String[][] multiplicationTable = PrimeMultiplicationTable.createMultiplicationTable(tableFirstRowAndColumn);
    String[] expectedFirstRow = {"2", "4", "6", "10", "14", "22", "26", "34", "38", "46", "58"};
    String[] expectedSecondRow = {"3", "6", "9", "15", "21", "33", "39", "51", "57", "69", "87"};
    String[] expectedSecondLastRow = {"23", "46", "69", "115", "161", "253", "299", "391", "437", "529", "667"};
    String[] expectedLastRow = {"29", "58", "87", "145", "203", "319", "377", "493", "551", "667", "841"};

    assertAll(
      () -> assertThat(listOfPrimeNumbers, allOf(is(notNullValue()), is(not(empty())), hasSize(10))),
      () -> assertThat(tableFirstRowAndColumn, is(notNullValue())),
      () -> assertThat(tableFirstRowAndColumn.length, is(11)),
      () -> assertThat(tableFirstRowAndColumn[0], is(arrayWithSize(11))),
      () -> assertThat(tableFirstRowAndColumn[1], is(arrayWithSize(11))),
      () -> assertThat(tableFirstRowAndColumn[2], is(arrayWithSize(11))),
      () -> assertThat(multiplicationTable.length, is(11)),
      () -> assertThat(multiplicationTable[1], is(arrayWithSize(11))),
      () -> assertThat(multiplicationTable[2], is(arrayWithSize(11))),
      () -> assertThat(multiplicationTable[multiplicationTable.length - 1], is(arrayWithSize(11))),
      () -> assertThat(multiplicationTable[multiplicationTable.length - 2], is(arrayWithSize(11))),
      () -> assertThat(Arrays.equals(expectedFirstRow, multiplicationTable[1]), is(true)),
      () -> assertThat(Arrays.equals(expectedSecondRow, multiplicationTable[2]), is(true)),
      () -> assertThat(Arrays.equals(expectedSecondLastRow, multiplicationTable[multiplicationTable.length - 2]), is(true)),
      () -> assertThat(Arrays.equals(expectedLastRow, multiplicationTable[multiplicationTable.length - 1]), is(true))
    );
  }

  @Test
  @DisplayName("Creates multiplication table with expected first row and column when num is 0")
  void testCreateMultiplicationTableFor0PrimeNumbers() {
    int num = 0;
    List<Integer> listOfPrimeNumbers = PrimeMultiplicationTable.listOfPrimeNumbers(num);
    String[][] tableFirstRowAndColumn = PrimeMultiplicationTable.createTableFirstRowAndColumn(listOfPrimeNumbers);
    String[][] multiplicationTable = PrimeMultiplicationTable.createMultiplicationTable(tableFirstRowAndColumn);

    assertAll(
      () -> assertThat(listOfPrimeNumbers, allOf(is(notNullValue()), is(empty()))),
      () -> assertThat(tableFirstRowAndColumn, is(notNullValue())),
      () -> assertThat(tableFirstRowAndColumn.length, is(0)),
      () -> assertThat(multiplicationTable, is(notNullValue())),
      () -> assertThat(multiplicationTable.length, is(0))
    );
  }

  @Test
  @DisplayName("Tests the expected multiplication table to be printed when num is 10")
  void testPrintMultiplicationTableFor10PrimeNumbers() {
    int num = 10;
    List<Integer> listOfPrimeNumbers = PrimeMultiplicationTable.listOfPrimeNumbers(num);
    String[][] tableFirstRowAndColumn = PrimeMultiplicationTable.createTableFirstRowAndColumn(listOfPrimeNumbers);
    String[][] multiplicationTable = PrimeMultiplicationTable.createMultiplicationTable(tableFirstRowAndColumn);
    String multiplicationTableStr = PrimeMultiplicationTable.printMultiplicationTable(multiplicationTable);
    String expectedMultiplicationTableStr =
      "-   2   3   5   7   11  13  17  19  23  29  \n" +
      "2   4   6   10  14  22  26  34  38  46  58  \n" +
      "3   6   9   15  21  33  39  51  57  69  87  \n" +
      "5   10  15  25  35  55  65  85  95  115 145 \n" +
      "7   14  21  35  49  77  91  119 133 161 203 \n" +
      "11  22  33  55  77  121 143 187 209 253 319 \n" +
      "13  26  39  65  91  143 169 221 247 299 377 \n" +
      "17  34  51  85  119 187 221 289 323 391 493 \n" +
      "19  38  57  95  133 209 247 323 361 437 551 \n" +
      "23  46  69  115 161 253 299 391 437 529 667 \n" +
      "29  58  87  145 203 319 377 493 551 667 841 \n";

    assertAll(
      () -> assertThat(listOfPrimeNumbers, allOf(is(notNullValue()), is(not(empty())), hasSize(10))),
      () -> assertThat(tableFirstRowAndColumn, is(notNullValue())),
      () -> assertThat(tableFirstRowAndColumn.length, is(11)),
      () -> assertThat(multiplicationTable, is(notNullValue())),
      () -> assertThat(multiplicationTable.length, is(11)),
      () -> assertThat(multiplicationTableStr, is(expectedMultiplicationTableStr))
    );
  }

  @Test
  @DisplayName("Tests the expected multiplication table to be printed when num is 0")
  void testPrintMultiplicationTableFor0PrimeNumbers() {
    int num = 0;
    List<Integer> listOfPrimeNumbers = PrimeMultiplicationTable.listOfPrimeNumbers(num);
    String[][] tableFirstRowAndColumn = PrimeMultiplicationTable.createTableFirstRowAndColumn(listOfPrimeNumbers);
    String[][] multiplicationTable = PrimeMultiplicationTable.createMultiplicationTable(tableFirstRowAndColumn);
    String multiplicationTableStr = PrimeMultiplicationTable.printMultiplicationTable(multiplicationTable);
    String expectedMultiplicationTableStr = "-";

    assertAll(
      () -> assertThat(listOfPrimeNumbers, allOf(is(notNullValue()), is(empty()))),
      () -> assertThat(tableFirstRowAndColumn, is(notNullValue())),
      () -> assertThat(tableFirstRowAndColumn.length, is(0)),
      () -> assertThat(multiplicationTable, is(notNullValue())),
      () -> assertThat(multiplicationTable.length, is(0)),
      () -> assertThat(multiplicationTableStr, is(expectedMultiplicationTableStr))
    );
  }
}
