package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PaymentServicesPage extends BasePage {

    public PaymentServicesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//span[contains(@class, 'select__arrow')]")
    private WebElement selectArrow;

    @FindBy(how = How.XPATH, using = "//p[contains(@class, 'select__option') and contains(., 'связи')]")
    private WebElement mobileServicesOption;

    @FindBy(how = How.XPATH, using = "//p[contains(@class, 'select__option') and contains(., 'интернет')]")
    private WebElement internetServicesOption;

    @FindBy(how = How.XPATH, using = "//p[contains(@class, 'select__option') and contains(., 'Рассрочка')]")
    private WebElement installmentOption;

    @FindBy(how = How.XPATH, using = "//p[contains(@class, 'select__option') and contains(., 'Задолженность')]")
    private WebElement debtOption;

    @FindBy(how = How.XPATH, using = "//span[contains(@class, 'select__now')]")
    private WebElement selectedServiceText;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Номер телефона']")
    private WebElement phoneNumberInput;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Номер абонента']")
    private WebElement internetPhoneInput;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Номер счета на 44']")
    private WebElement scoreInstalment;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Номер счета на 2073']")
    private WebElement scoreArrears;

    @FindBy(how = How.XPATH, using = "//input[@id='connection-sum']")
    private WebElement connectionSumInput;

    @FindBy(how = How.XPATH, using = "//input[@id='internet-sum']")
    private WebElement internetSumInput;

    @FindBy(how = How.XPATH, using = "//input[@id='instalment-sum']")
    private WebElement instalmentSumInput;

    @FindBy(how = How.XPATH, using = "//input[@id='arrears-sum']")
    private WebElement arrearsSumInput;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='E-mail для отправки чека']")
    private WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@id='instalment-email'and@placeholder='E-mail для отправки чека']")
    private WebElement emailInstalmentInput;

    @FindBy(how = How.XPATH, using = "//input[@id='arrears-email'and@placeholder='E-mail для отправки чека']")
    private WebElement emailArrearsInput;

    @FindBy(how = How.XPATH, using = "//button[@class='button button__default ']")
    private WebElement continueButton;

    @FindBy(how = How.XPATH, using = "//h2[contains(text(),'Онлайн пополнение')]")
    private WebElement blockTitle;

    protected void clickElementWithJS(WebElement element) {
        waitForElementVisible(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void openServicesDropdown() {
        clickElement(selectArrow);
        wait.until(ExpectedConditions.visibilityOf(mobileServicesOption));
    }

    public void selectMobileServices() {
        openServicesDropdown();
        clickElementWithJS(mobileServicesOption);
        waitForServiceSelected("Услуги связи");
        waitForElementVisible(phoneNumberInput);
        waitForElementVisible(connectionSumInput);
    }

    public void selectInternetServices() {
        openServicesDropdown();
        clickElementWithJS(internetServicesOption);
        waitForServiceSelected("Домашний интернет");
        waitForElementVisible(internetPhoneInput);
        waitForElementVisible(internetSumInput);
    }

    public void selectInstallment() {
        openServicesDropdown();
        clickElementWithJS(installmentOption);
        waitForServiceSelected("Рассрочка");
        waitForElementVisible(scoreInstalment);
        waitForElementVisible(instalmentSumInput);
    }

    public void selectDebt() {
        openServicesDropdown();
        clickElementWithJS(debtOption);
        waitForServiceSelected("Задолженность");
        waitForElementVisible(scoreArrears);
        waitForElementVisible(arrearsSumInput);
    }

    private void waitForServiceSelected(String serviceName) {
        wait.until(ExpectedConditions.textToBePresentInElement(selectedServiceText, serviceName));
    }

    public void enterMobilePhoneNumber(String phoneNumber) {
        waitForElementVisible(phoneNumberInput);
        typeText(phoneNumberInput, phoneNumber);
    }


    public void enterMobileAmount(String amount) {
        waitForElementVisible(connectionSumInput);
        typeText(connectionSumInput, amount);
    }

    public String getMobilePhonePlaceholder() {
        return getElementPlaceholder(phoneNumberInput);
    }

    public String getEmailPlaceholder() {
        return getElementPlaceholder(emailInput);
    }

    public String getEmailInstalmentPlaceholder() {
        return getElementPlaceholder(emailInstalmentInput);
    }

    public String getEmailArrearsPlaceholder() {
        return getElementPlaceholder(emailArrearsInput);
    }

    public String getInternetPhonePlaceholder() {
        return getElementPlaceholder(internetPhoneInput);
    }

    public String getInstallmentAccountPlaceholder() {
        return getElementPlaceholder(scoreInstalment);
    }

    public String getDebtAccountPlaceholder() {
        return getElementPlaceholder(scoreArrears);
    }

    public String getMobileAmountPlaceholder() {
        return getElementPlaceholder(connectionSumInput);
    }

    public String getInternetAmountPlaceholder() {
        return getElementPlaceholder(internetSumInput);
    }

    public String getInstallmentAmountPlaceholder() {
        return getElementPlaceholder(instalmentSumInput);
    }

    public String getDebtAmountPlaceholder() {
        return getElementPlaceholder(arrearsSumInput);
    }

    public void clickContinue() {
        clickElement(continueButton);
    }

    public boolean isContinueButtonEnabled() {
        return continueButton.isEnabled();
    }

}
