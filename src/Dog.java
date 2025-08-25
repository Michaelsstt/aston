public class Dog extends Animal {
    private static int dogsCount = 0;
    private final int maxRunDistance = 500;
    private final int maxSwimDistance = 10;

    public Dog(String name) {
        super(name);
    }

    @Override
    public void run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println(getName() + " пробежал " + distance + " m.");
        } else {
            System.out.println(getName() + " не может столько пробежать " + distance + " m.");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= maxSwimDistance) {
            System.out.println(getName() + " проплыл " + distance + " m.");
        } else {
            System.out.println(getName() + " не может столько проплыть " + distance + " m.");
        }
    }

    public static int getDogsCount() {
        return dogsCount;
    }
}
