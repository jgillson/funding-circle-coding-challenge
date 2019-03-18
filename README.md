# Funding Circle Coding Challenge

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

3. To change the default of 10 to another value, pass the `Pargs=num` where `num` is the desired value.

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
