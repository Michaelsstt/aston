import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.*;

public class ArithmeticMethodsTestNG {
    @DataProvider
    public Object[][] addData() {
        return new Object[][]{
                {5, 3, 8},
                {-2, 7, 5},
                {0, 0, 0},
                {100, -50, 50}
        };
    }

    @Test(dataProvider = "addData")
    public void testAdd(int a, int b, int expected) {
        assertEquals(ArithmeticMethods.add(a, b), expected);
    }

    @DataProvider
    public Object[][] subtractData() {
        return new Object[][]{
                {10, 4, 6},
                {5, -3, 8},
                {0, 0, 0},
                {-5, -3, -2}
        };
    }

    @Test(dataProvider = "subtractData")
    public void testSubtract(int a, int b, int expected) {
        assertEquals(ArithmeticMethods.subtract(a, b), expected);
    }

    @DataProvider
    public Object[][] multiplyData() {
        return new Object[][]{
                {4, 5, 20},
                {-3, 6, -18},
                {0, 5, 0},
                {-2, -4, 8}
        };
    }

    @Test(dataProvider = "multiplyData")
    public void testMultiply(int a, int b, int expected) {
        assertEquals(ArithmeticMethods.multiply(a, b), expected);
    }

    @DataProvider
    public Object[][] divideData() {
        return new Object[][]{
                {10, 2, 5},
                {9, 3, 3},
                {0, 5, 0},
                {-10, 2, -5}
        };
    }

    @Test(dataProvider = "divideData")
    public void testDivide(int a, int b, int expected) {
        assertEquals(ArithmeticMethods.divide(a, b), expected);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivideByZero() {
        ArithmeticMethods.divide(10, 0);
    }
}
