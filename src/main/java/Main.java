import java.util.Arrays;

public class Main {


    public static void main(String[] args) {

    }

    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() {
        int a = 10;
        int b = 20;
        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() {
        int value = 100;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
        int a = 10;
        int b = 20;
        if (a >= b) {
            System.out.println("a>=b");
        } else {
            System.out.println("a<b");
        }
    }
    //5
    public static boolean sumRange(int a, int b) {
        int sum = a + b;
        boolean isSumInRange = (sum >= 10) && (sum <= 20);
        return isSumInRange;
    }
    //6
    public static void checkNumber(int num) {
        if (num >= 0) {
            System.out.println("Число Положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }
    //7
    public static boolean isNegative(int num) {
        return num < 0;
    }
    //8
    public static void printStr (String str, int num) {
        for (int i = 0; i < num; i++) {
            System.out.println(str);
        }
    }
    //9
    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        }
        if (year % 100 == 0) {
            return false;
        }
        return year % 4 == 0;
    }
    //10
     {
        int [] array = { 1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] == 0) ? 1 : 0;
        }

    }
    //11
    {
        int[] array = new int[100];
        for (int i = 0; i < array.length ; i++) {
            array[i] = i +1;
        }
    }
    //12
    {
        int[] array = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
    }
    //13
    {
        int [][] matrix = new int[5][5];
        for (int i = 0; i < 5; i++) {
            matrix[i][i] = 1;
            matrix[i][5 - 1 - i] = 1;
        }
    }
    //14
    public static int[] array(int len, int initialValue) {
        int [] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }
        return array;
    }

}
