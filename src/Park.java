public class Park {

    class Attractions {
        private String name;
        private String workingTime;
        private double price;

        public Attractions(String name, String workingTime, double price) {
            this.name = name;
            this.workingTime = workingTime;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getWorkingTime() {
            return workingTime;
        }

        public double getPrice() {
            return price;
        }
    }
}
