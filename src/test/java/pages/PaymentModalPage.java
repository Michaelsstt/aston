package pages;

import config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class PaymentModalPage extends BasePage {

    public PaymentModalPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'pay-description__text')]")
    private WebElement phoneNumberInfo;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'pay-description__cost')]")
    private WebElement amountInfo;

    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'colored')]")
    private WebElement payButton;


    @FindBy(how = How.XPATH, using = "//input[@id='cc-number']")
    private WebElement cardNumberInput;

    @FindBy(how = How.XPATH, using = "//input[@autocomplete='cc-exp']")
    private WebElement expiryDateInput;

    @FindBy(how = How.XPATH, using = "//input[@autocomplete='cc-csc']")
    private WebElement cvvInput;

    @FindBy(how = How.XPATH, using = "//input[@autocomplete='cc-name']")
    private WebElement cardholderNameInput;


    @FindBy(how = How.XPATH, using = "//img[contains(@src, 'assets/images/payment-icons/card-types/visa-system.svg')]")
    private WebElement visaLogo;

    @FindBy(how = How.XPATH, using = "//img[contains(@src, 'assets/images/payment-icons/card-types/mastercard-system.svg')]")
    private WebElement mastercardLogo;

    @FindBy(how = How.XPATH, using = "//img[contains(@src, 'assets/images/payment-icons/card-types/belkart-system.svg')]")
    private WebElement belkartLogo;


    @FindBy(how = How.XPATH, using = "//label[@for='cc-number'] | //label[contains(text(), 'Номер карты')]")
    private WebElement cardNumberLabel;

    @FindBy(how = How.XPATH, using = "//label[@for='cc-exp'] | //label[contains(text(), 'Срок действия')]")
    private WebElement expiryDateLabel;

    @FindBy(how = How.XPATH, using = "//label[@for='cc-csc'] | //label[contains(text(), 'CVC')]")
    private WebElement cvvLabel;

    @FindBy(how = How.XPATH, using = "//label[@for='cc-name'] | //label[contains(text(), 'Имя и фамилия на карте')]")
    private WebElement cardholderNameLabel;


    public String getCardNumberLabelText() {
        try {
            waitForElementVisibility(cardNumberLabel);
            return cardNumberLabel.getText().trim();
        } catch (Exception e) {
            try {
                return getElementPlaceholder(cardNumberInput);
            } catch (Exception ex) {
                return "Не найдено";
            }
        }
    }

    public String getExpiryDateLabelText() {
        try {
            waitForElementVisibility(expiryDateLabel);
            return expiryDateLabel.getText().trim();
        } catch (Exception e) {
            try {
                return getElementPlaceholder(expiryDateInput);
            } catch (Exception ex) {
                return "Не найдено";
            }
        }
    }

    public String getCvvLabelText() {
        try {
            waitForElementVisibility(cvvLabel);
            return cvvLabel.getText().trim();
        } catch (Exception e) {
            try {
                return getElementPlaceholder(cvvInput);
            } catch (Exception ex) {
                return "Не найдено";
            }
        }
    }

    public String getCardholderNameLabelText() {
        try {
            waitForElementVisibility(cardholderNameLabel);
            return cardholderNameLabel.getText().trim();
        } catch (Exception e) {
            try {
                return getElementPlaceholder(cardholderNameInput);
            } catch (Exception ex) {
                return "Не найдено";
            }
        }
    }

    public String getPhoneNumberFromModal() {
        waitForElementVisibility(phoneNumberInfo);
        String fullText = phoneNumberInfo.getText();
        String digitsOnly = fullText.replaceAll("[^0-9]", "");

        if (digitsOnly.contains(TestConfig.TEST_PHONE_NUMBER)) {

            return TestConfig.TEST_PHONE_NUMBER.substring(3);
        }

        return digitsOnly;
    }

    public String getAmountFromModal() {
        waitForElementVisibility(amountInfo);
        return getElementText(amountInfo);
    }


    public String getPayButtonAmount() {
        waitForElementVisibility(payButton);
        return payButton.getText();
    }


    public boolean isVisaLogoDisplayed() {
        waitForElementVisibility(visaLogo);
        return visaLogo.isDisplayed();
    }


    public boolean isMastercardLogoDisplayed() {
        waitForElementVisibility(mastercardLogo);
        return mastercardLogo.isDisplayed();
    }


    public boolean isBelkartLogoDisplayed() {
        waitForElementVisibility(belkartLogo);
        return belkartLogo.isDisplayed();
    }


    public boolean isPaymentModalDisplayed() {
        waitForElementVisibility(phoneNumberInfo);
        waitForElementVisibility(amountInfo);
        return phoneNumberInfo.isDisplayed() && amountInfo.isDisplayed();
    }

    private void waitForElementVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}