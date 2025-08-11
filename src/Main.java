public class Main {
    public static void main(String[] args) {
        String[][] data = {
                {"1", "2", "3", "4"},
                {"2", "3", "4", "5"},
                {"3", "4", "5", "6"},
                {"4", "5", "6", "7"}
        };

        try {
            int sum = array(data);
            System.out.println("сумма всех чисел " + sum);
        } catch (MyArraySizeException e) {
            System.err.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.err.println(e.getMessage());
        }

        try {
            int[][] numbers = {{1, 2}, {2, 3}};
            System.out.println(numbers[1][2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("ошибка индекса " + e.getMessage());
        }

    }

    public static class MyArraySizeException extends RuntimeException {
        public MyArraySizeException(String message) {
            super(message);
        }
    }

    public static class MyArrayDataException extends RuntimeException {
        public MyArrayDataException(String message) {
            super(message);
        }
    }

    public static int array(String[][] array) {
        if (array == null || array.length != 4) {
            throw new MyArraySizeException("Размер массива должен быть 4x4");
        }
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null || array[i].length != 4) {
                throw new MyArraySizeException("строка " + i + " не содержит 4 элемента");
            }
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в ячейке [" + i + "][" + j + "]: " + array[i][j]);
                }
            }
        }
        return sum;

    }
}
