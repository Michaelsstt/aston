public class Main {
    public static void main(String[] args) {
        Product[] productsarray = new Product[5];
        productsarray[0] = new Product("Samsung s25 ultra", "01.02.2025", "samsung corp", "Korea", 5599, true);
        productsarray[0] = new Product("Samsung s40 Pro", "01.02.2030", "samsung corp", "Korea", 7000, false);
        productsarray[2] = new Product("iPhone 99", "01.01.2040", "Apple", "USA", 9999, false);
        productsarray[3] = new Product("iPhone 100", "01.01.2050", "Apple", "USA", 100000, false);
        productsarray[4] = new Product("iPhone 1", "01.01.2008", "Apple", "USA", 500, false);
    }
}
