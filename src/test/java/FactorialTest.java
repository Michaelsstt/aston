import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class FactorialTest {
    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 1",
            "5, 120",
            "10, 3628800"
    })
    void testCalculateFactorial(int num, long expected) {
        assertEquals(expected, Factorial.calculateFactorial(num));
    }
    @Test
    void testCalculateFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            Factorial.calculateFactorial(-5);
        });
    }
}
