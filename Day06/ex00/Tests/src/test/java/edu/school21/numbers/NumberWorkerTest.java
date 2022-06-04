package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {

    NumberWorker numberWorker;

    @BeforeEach
    void setNumberWorker() {
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = { 95219, 11329, 10091 })
    void isPrimeForPrimes(int number) {
        Assertions.assertTrue(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = { 91239, 12319, 19011 })
    void isPrimeForNotPrimes(int number) {
        Assertions.assertFalse(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 0, -5, -15, -4545 })
    void isPrimeForIncorrectNumbers(int number) {
        Assertions.assertThrows(NumberWorker.IllegalNumberException.class, () -> numberWorker.isPrime(number));
    }


    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"}, delimiter = ',')
    void checkDigitSum(int x, int y) {
        Assertions.assertEquals(numberWorker.digitsSum(x), y);
    }

}
