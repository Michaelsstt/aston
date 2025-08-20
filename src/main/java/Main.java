import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        //факториал числа
        int number = 5;
        if (number < 0) {
            System.out.println("Факториал отрицательного числа не определен.");
        } else {
            long factorial = Factorial.calculateFactorial(number);
            System.out.println("Факториал числа " + number + " = " + factorial);
        }

        //площадь треугольника
        double sideA = 3.0;
        double sideB = 4.0;
        double sideC = 3.0;

        double area = Triangle.calculateArea(sideA, sideB, sideC);
        BigDecimal bd = new BigDecimal(area);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Площадь треугольника = " + bd.doubleValue());

        // арифметические действия
        int a = 5;
        int b = 4;

        System.out.println("Сложение " + a + " + " + b + " = " + ArithmeticMethods.add(a,b));
        System.out.println("Вычитание " + a + " - " + b + " = " + ArithmeticMethods.subtract(a,b));
        System.out.println("Умножение " + a + " * " + b + " = " + ArithmeticMethods.multiply(a,b));
        System.out.println("Деление " + a + " / " + b + " = " + ArithmeticMethods.divide(a,b));

        //сравнение двух целых числа
        int q = 8;
        int w = 6;
        NumberCompare.compareNumbers(q,w);
    }



}
