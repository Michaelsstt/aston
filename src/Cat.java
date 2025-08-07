public class Cat extends Animal {
    private static int catsCount = 0;
    private final int maxRunDistance = 200;
    private boolean isHungry = true;

    public Cat(String name) {
        super(name);
        catsCount++;



    }
    @Override
    public void run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println(getName() + " пробежал " + distance + " m.");
        } else {
            System.out.println(getName() + " не может пробежать столько " + distance + " m.");
        }
    }
    @Override
    public void swim(int distance) {
        System.out.println(getName() + " не умеет плавать!");
    }

    public void eatFromBowl(Bowl bowl, int amount) {
        if (bowl.getFoodAmount() >= amount) {
            bowl.decreaseFood(amount);
            isHungry = false;
            System.out.println(getName() + " поел и теперь сыт");
        } else {
            System.out.println(getName() + " не стал есть, в миске мало еды");
        }
    }

    public static int totalCatsCount() {
        return catsCount;
    }
    public boolean isHungry() {
        return isHungry;
    }




}
