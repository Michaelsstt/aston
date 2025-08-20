public class NumberCompare {
    public static String compareNumbers(int num1, int num2) {
        if (num1 > num2) {
            return num1 + " > " + num2;
        } else if (num1 < num2) {
            return num1 + " < " + num2;
        } else {
            return num1 + " == " + num2;
        }
    }
}
