package pages;

import config.TestConfig;
import org.openqa.selenium.WebDriver;


public class OnlinePaymentPage extends BasePage {

    private PaymentServicesPage paymentServicesPage;
    private PaymentModalPage paymentModalPage;

    public OnlinePaymentPage(WebDriver driver) {
        super(driver);
        this.paymentServicesPage = new PaymentServicesPage(driver);
        this.paymentModalPage = new PaymentModalPage(driver);
    }

    public void navigateToPaymentPage() {
        driver.get(TestConfig.BASE_URL);
        acceptCookiesIfPresent();
    }

    public PaymentServicesPage getPaymentServicesPage() {
        return paymentServicesPage;
    }

    public PaymentModalPage getPaymentModalPage() {
        return paymentModalPage;
    }

}