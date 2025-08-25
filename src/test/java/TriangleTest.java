import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {
    @ParameterizedTest
    @CsvSource({
            "3, 4, 5, 6.0",
            "5, 5, 6, 12.0",
            "7, 8, 9, 26.8328"
    })
    void testCalculateArea(double a, double b, double c, double expected) {
        assertEquals(expected, Triangle.calculateArea(a, b, c), 0.0001);
    }

    @Test
    void testCalculateAreaNegativeSide() {
        assertThrows(IllegalArgumentException.class, () -> {
            Triangle.calculateArea(-3, 4, 5);
        });
    }
}
