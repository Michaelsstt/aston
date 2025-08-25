
public class Product {
    private String name;
    private String dateProduction;
    private String manufacturer;
    private String countryOfOrigin;
    private int price;
    private boolean booking;

    public Product(String name, String dateProduction, String manufacturer, String countryOfOrigin, int price, boolean booking) {
        this.name = name;
        this.dateProduction = dateProduction;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.booking = booking;
    }

    public void printProductInfo() {
        System.out.println("название" + name);
        System.out.println("дата производства" + dateProduction);
        System.out.println("производитель" + manufacturer);
        System.out.println("страна происхождения" + countryOfOrigin);
        System.out.println("цена" + price + "руб");
        System.out.println("статус бронирования" + booking);
    }
}
