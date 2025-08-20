import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberCompareTest {

    @ParameterizedTest
    @CsvSource({
            "10, 5, '10 > 5'",
            "3, 7, '3 < 7'",
            "8, 8, '8 == 8'",
            "-2, -5, '-2 > -5'",
            "0, 0, '0 == 0'"
    })
    void testCompareNumbers(int num1, int num2, String excepted) {
        String result = NumberCompare.compareNumbers(num1, num2);
        assertEquals(excepted, result);
    }
}
