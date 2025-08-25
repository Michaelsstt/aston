import java.util.ArrayList;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {
        Dog dog1 = new Dog("Rex");
        Dog dog2 = new Dog("Santa'sLilHelper");
        Cat cat1 = new Cat("Черныш");
        Cat cat2 = new Cat("Мурка");
        Cat cat3 = new Cat("Mr.Bigglesworth");

        dog1.run(300);
        dog1.run(3000);
        dog1.swim(5);
        dog1.swim(50);

        cat1.run(100);
        cat1.run(1000);
        cat1.swim(10);

        Bowl bowl = new Bowl(30);

        ArrayList<Cat> cats = new ArrayList<>();
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);

        for (Cat cat : cats) {
            cat.eatFromBowl(bowl, 15);
        }

        for (Cat cat : cats) {
            System.out.println(cat.getName() + " голоден: " + cat.isHungry());
        }

        bowl.addFood(30);

        for (Cat cat : cats) {
            if (cat.isHungry()) {
                cat.eatFromBowl(bowl, 15);
            }
        }

        //2
        Circle circle = new Circle(6, "red", "blue");
        Rectangle rectangle = new Rectangle(5.0, 7, "red", "white");
        Triangle triangle = new Triangle(3, 4, 4, "green", "black");

        System.out.println("круг:");
        circle.printInfo();

        System.out.println("прямоугольник:");
        rectangle.printInfo();

        System.out.println("треугольник:");
        triangle.printInfo();
    }
}
