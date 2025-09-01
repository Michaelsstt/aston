package pages;

import config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class OnlinePaymentPage extends BasePage {

    public OnlinePaymentPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//span[contains(@class, 'select__now') and contains(., 'связи')]")
    private WebElement servicesTab;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Номер телефона']")
    private WebElement phoneNumberInput;

    @FindBy(how = How.XPATH, using = "//input[@id='connection-sum']")
    private WebElement amountInput;

    @FindBy(how = How.XPATH, using = "//h2[contains(text(),'Онлайн пополнение')]")
    private WebElement blockTitle;

    @FindBy(how = How.XPATH, using =
            "//img[" +
                    "contains(@src, 'visa') or contains(@alt, 'Visa') or contains(@alt, 'Verified by Visa') or " +
                    "contains(@src, 'mastercard') or contains(@alt, 'MasterCard') or contains(@alt, 'SecureCode') or " +
                    "contains(@src, 'belkart')]"
    )
    private List<WebElement> paymentSystemLogos;

    @FindBy(how = How.XPATH, using = "//button[contains(., 'Продолжить') and contains(@class, 'button')]")
    private WebElement continueButton;

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Подробнее о сервисе')]")
    private WebElement detailsLink;

    public void navigateToPaymentPage() {
        driver.get(TestConfig.BASE_URL);
        acceptCookiesIfPresent();
    }

    public void openPaymentsSection() { // Открываем нужный раздел
        scrollToElement(servicesTab);
        clickElement(servicesTab);
    }

    public String getBlockTitle() {
        waitForElementVisible(blockTitle);
        return blockTitle.getText();
    }

    public int getPaymentSystemLogosCount() {
        return paymentSystemLogos.size();
    }

    public boolean arePaymentLogosDisplayed() {
        if (paymentSystemLogos.isEmpty()) {
            return false;
        }
        return paymentSystemLogos.stream().allMatch(WebElement::isDisplayed);
    }

    public void enterPhoneNumber(String phoneNumber) {
        scrollToElement(phoneNumberInput);
        typeText(phoneNumberInput, phoneNumber);
    }

    public void enterAmount(String amount) {
        scrollToElement(amountInput);
        typeText(amountInput, amount);
    }

    public boolean isContinueButtonEnabled() {
        return continueButton.isEnabled();
    }
}
