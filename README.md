## Objective
Write a program that prints out a multiplication table of the first 10 prime numbers.
* The program must run from the command line and print one table to STDOUT.
* The first row and column of the table should have the 10 primes, with each cell containing the product of the primes for the corresponding row and column.

## Technologies Used
* Java 8 (jdk1.8.0_201)
* Gradle 5.2.1
* IntelliJ IDEA 2018.3.5
* JUnit Jupiter 5.4.1
* Hamcrest 2.1
* Commons Collections 4.3

## Usage
1. Clone [repo](https://github.com/jgillson/funding-circle-coding-challenge.git)
2. Run `./gradlew run` (The first run will download the gradle wrapper and any associated dependencies - by default, `num` is 10).

Example:
```
$ ./gradlew run
Downloading https://services.gradle.org/distributions/gradle-5.2.1-bin.zip
...................................................................................

Welcome to Gradle 5.2.1!

Here are the highlights of this release:
 - Define sets of dependencies that work together with Java Platform plugin
 - New C++ plugins with dependency management built-in
 - New C++ project types for gradle init
 - Service injection into plugins and project extensions

For more details see https://docs.gradle.org/5.2.1/release-notes.html

Starting a Gradle Daemon (subsequent builds will be faster)

> Task :run
-   2   3   5   7   11  13  17  19  23  29
2   4   6   10  14  22  26  34  38  46  58
3   6   9   15  21  33  39  51  57  69  87
5   10  15  25  35  55  65  85  95  115 145
7   14  21  35  49  77  91  119 133 161 203
11  22  33  55  77  121 143 187 209 253 319
13  26  39  65  91  143 169 221 247 299 377
17  34  51  85  119 187 221 289 323 391 493
19  38  57  95  133 209 247 323 361 437 551
23  46  69  115 161 253 299 391 437 529 667
29  58  87  145 203 319 377 493 551 667 841
```

3. To change the default of 10 to another value, pass the `-Pargs=num` where `num` is the desired value.

Example:
```
$ ./gradlew run -Pargs=5

> Task :run
-   2   3   5   7   11
2   4   6   10  14  22
3   6   9   15  21  33
5   10  15  25  35  55
7   14  21  35  49  77
11  22  33  55  77  121
```

4.  To test negative cases such as changing the number to a negative integer.

Example:
```
$ ./gradlew run -Pargs=-1

> Task :run FAILED
Invalid integer - please enter a positive integer

FAILURE: Build failed with an exception.
```

## Testing
JUnit Jupiter was used as the testing framework.
To run all the tests from the command line, run `./gradlew clean test`

Example:
```
$ ./gradlew clean test

> Task :test
Running test: Test testPrintMultiplicationTableFor0PrimeNumbers()(PrimeMultiplicationTableTests)
Running test: Test testEmptyListOfPrimes()(PrimeMultiplicationTableTests)
Running test: Test testPrintMultiplicationTableFor10PrimeNumbers()(PrimeMultiplicationTableTests)
Running test: Test testCreateMultiplicationTableFor0PrimeNumbers()(PrimeMultiplicationTableTests)
Running test: Test testInvalidPrimes()(PrimeMultiplicationTableTests)
Running test: Test testListOfPrimes()(PrimeMultiplicationTableTests)
Running test: Test testCreateMultiplicationTableFor10PrimeNumbers()(PrimeMultiplicationTableTests)
Running test: Test testCreateTableFirstRowAndColumnFor20PrimeNumbers()(PrimeMultiplicationTableTests)
Running test: Test testCreateTableFirstRowAndColumnFor0PrimeNumbers()(PrimeMultiplicationTableTests)
Running test: Test testCreateTableFirstRowAndColumnFor10PrimeNumbers()(PrimeMultiplicationTableTests)
Running test: Test testValidPrimes()(PrimeMultiplicationTableTests)
```

To view the test reports in .html format, navigate to `build/reports/tests/test/index.html` and open in any browser.

## Methods
### `static List<Integer> listOfPrimeNumbers(int n)`
Returns a List of Integers of the first `n` prime numbers.
The method returns the List of Integers of `n` prime numbers, adding a number to the list if it's prime.  
The prime number of 2 is added to the list, and testing starts at index 3. 
The index is then incremented by 2 so the candidate numbers are always odd.

The time complexity of this method is `O(n)` as the growth order is linear as the size of `n` increases.

### `static boolean isPrimeNumber(int n)`
Checks if a number is prime by checking for divisibility on numbers less than it.  
It only needs to go up to the square root of `n` because if `n` is divisible by a number greater than its square root, then it's divisible by something smaller than it.
The work inside the for loop is constant.
Therefore we just need to know how many iterations the for loop goes through in the worst case.

The time complexity of this method is `O(sqrt(n))` as the for loop will start when x = 2 and end when x * x <= n.
Or, in other words, it stops when x = sqrt(n).

### `static String[][] createTableFirstRowAndColumn(List<Integer> listOfPrimeNumbers)`
Creates the initial table with the first row and column represented by the passed in list of prime numbers.
A 2D array is used here to create the initial row (first array in the 2D array) and then the columns are generated where each prime number is the first element in its own array.

The time complexity of this method is `O(2n) -> O(n)` as the growth order is linear and iterates twice over the list of prime numbers.

Example (n is 10):
```
-   2   3   5   7   11  13  17  19  23  29
2   
3   
5   
7   
11  
13  
17  
19  
23  
29  
```

### `static String[][] createMultiplicationTable(String[][] table)`
Creates the prime number multiplication table as a 2D array with the products of `n` prime numbers.

The time complexity of this method is `O(n^2)` due to the nested for loops to create the prime number multiplication table with each product.

Example (n is 10):
```
-   2   3   5   7   11  13  17  19  23  29
2   4   6   10  14  22  26  34  38  46  58
3   6   9   15  21  33  39  51  57  69  87
5   10  15  25  35  55  65  85  95  115 145
7   14  21  35  49  77  91  119 133 161 203
11  22  33  55  77  121 143 187 209 253 319
13  26  39  65  91  143 169 221 247 299 377
17  34  51  85  119 187 221 289 323 391 493
19  38  57  95  133 209 247 323 361 437 551
23  46  69  115 161 253 299 391 437 529 667
29  58  87  145 203 319 377 493 551 667 841
```

### `static String printMultiplicationTable(String[][] multiplicationTable)`
Returns the prime number multiplication table as a String and prints this to the STDOUT.

The time complexity of this method is `O(n^2)` due to the nested for loops to print the prime number multiplication table.

## Summary
To generate the prime number multiplication table results in an overall time complexity of `O(n^2)`.  
Because the entire prime number multiplication table has to be generated, this scales poorly for `n` primes.
