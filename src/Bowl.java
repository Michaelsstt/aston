public class Bowl {
    private int foodAmount;

    public Bowl(int initialAmount) {
        this.foodAmount = initialAmount;
    }

    public int getFoodAmount() {
        return foodAmount;
    }
    public void decreaseFood(int amount) {
        if (amount <= foodAmount) {
            foodAmount -= amount;
        }
    }
    public void addFood(int amount) {
        foodAmount += amount;
    }

}
