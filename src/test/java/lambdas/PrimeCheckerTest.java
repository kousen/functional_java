package lambdas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PrimeCheckerTest {
    private final PrimeChecker primeChecker = new PrimeChecker();

    @ParameterizedTest(name = "{0} is prime and < 100")
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19,
            23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97})
    void isPrimeJava7(int x) {
        assertTrue(primeChecker.isPrimeJava7(x));
    }

    @ParameterizedTest(name = "{0} is prime and < 100")
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19,
            23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97})
    void isPrime(int x) {
        assertTrue(primeChecker.isPrime(x));
    }

    @Test
    void isComposite() {
        assertAll(
                () -> assertFalse(primeChecker.isPrimeJava7(4)),
                () -> assertFalse(primeChecker.isPrime(4))
        );
    }
}