import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticMethodsTest {
    @ParameterizedTest
    @CsvSource({
            "5, 3, 8",
            "-2, 7, 5",
            "0, 0, 0"
    })
    void testAdd(int a, int b, int expected) {
        assertEquals(expected, ArithmeticMethods.add(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "10, 4, 6",
            "5, -3, 8",
            "0, 0, 0",
            "-5, -3, -2"
    })
    void testSubtract(int a, int b, int expected) {
        assertEquals(expected, ArithmeticMethods.subtract(a, b));
    }
    @ParameterizedTest
    @CsvSource({
            "4, 5, 20",
            "-3, 6, -18",
            "0, 5, 0",
            "-2, -4, 8"
    })
    void testMultiply(int a, int b, int expected) {
        assertEquals(expected, ArithmeticMethods.multiply(a, b));
    }
    @ParameterizedTest
    @CsvSource({
            "10, 2, 5",
            "9, 3, 3",
            "0, 5, 0",
            "-10, 2, -5"
    })
    void testDivide(int a, int b, int expected) {
        assertEquals(expected, ArithmeticMethods.divide(a, b));
    }
    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {
            ArithmeticMethods.divide(10, 0);
        });
    }

}
