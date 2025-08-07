public interface GeometricFigures {
    default double calculatePerimeter() {
        return 0;
    }
    default double calculateArea() {
        return 0;
    }
    String getFillColor();
    String getBorderColor();

    default void printInfo() {
        System.out.println("периметр: " + calculatePerimeter());
        System.out.println("площадь: " + calculateArea());
        System.out.println("цвет фона: " + getFillColor());
        System.out.println("цвет границ: " + getBorderColor());
    }
}
