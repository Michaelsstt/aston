public class Factorial {
    public static long calculateFactorial(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("Факториал отрицательного числа не определен");
        }
        long result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}
