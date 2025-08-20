import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class FactorialTestNG {
    @DataProvider
    public Object[][] factorialData() {
        return new Object[][]{
                {0, 1},
                {1, 1},
                {5, 120},
                {10, 3628800}
        };
    }

    @Test(dataProvider = "factorialData")
    public void testCalculateFactorial(int num, long expected) {
        assertEquals(Factorial.calculateFactorial(num), expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateFactorialNegative() {
        Factorial.calculateFactorial(-5);
    }
}
