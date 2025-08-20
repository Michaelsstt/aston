import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.*;

public class TriangleTestNG {
    @DataProvider
    public Object[][] triangleData() {
        return new Object[][]{
                {3, 4, 5, 6.0},
                {5, 5, 6, 12.0},
                {7, 8, 9, 26.8328}
        };
    }

    @Test(dataProvider = "triangleData")
    public void testCalculateArea(double a, double b, double c, double expected) {
        assertEquals(Triangle.calculateArea(a, b, c), expected, 0.001);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateAreaWithNegativeSide() {
        Triangle.calculateArea(-3, 4, 5);
    }
}
