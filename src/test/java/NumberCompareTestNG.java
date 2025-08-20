import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.*;

public class NumberCompareTestNG {
    @DataProvider
    Object[][] compareData() {
        return new Object[][]{
                {10, 5, "10 > 5"},
                {3, 7, "3 < 7"},
                {8, 8, "8 == 8"},
                {-2, -5, "-2 > -5"},
                {0, 0, "0 == 0"}
        };
    }

    @Test(dataProvider = "compareData")
    public void testCompareNumbers(int num1, int num2, String expected) {
        assertEquals(NumberCompare.compareNumbers(num1, num2), expected);
    }
}
