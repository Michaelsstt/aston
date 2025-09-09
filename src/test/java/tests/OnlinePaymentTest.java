package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.OnlinePaymentPage;
import utils.WebDriverFactory;
import config.TestConfig;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OnlinePaymentTest {

    private WebDriver driver;
    private OnlinePaymentPage onlinePaymentPage;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        onlinePaymentPage = new OnlinePaymentPage(driver);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        onlinePaymentPage.navigateToPaymentPage();
    }

    @Test
    @Order(1)
    public void testEmptyFieldPlaceholdersForAllServices() {
        var servicesPage = onlinePaymentPage.getPaymentServicesPage();

        servicesPage.selectMobileServices();
        assertEquals("Номер телефона", servicesPage.getMobilePhonePlaceholder());
        assertEquals("Сумма", servicesPage.getMobileAmountPlaceholder());
        assertEquals("E-mail для отправки чека", servicesPage.getEmailPlaceholder());

        servicesPage.selectInternetServices();
        assertEquals("Номер абонента", servicesPage.getInternetPhonePlaceholder());
        assertEquals("Сумма", servicesPage.getInternetAmountPlaceholder());
        assertEquals("E-mail для отправки чека", servicesPage.getEmailPlaceholder());

        servicesPage.selectInstallment();
        assertEquals("Номер счета на 44", servicesPage.getInstallmentAccountPlaceholder());
        assertEquals("Сумма", servicesPage.getInstallmentAmountPlaceholder());
        assertEquals("E-mail для отправки чека", servicesPage.getEmailInstalmentPlaceholder());

        servicesPage.selectDebt();
        assertEquals("Номер счета на 2073", servicesPage.getDebtAccountPlaceholder());
        assertEquals("Сумма", servicesPage.getDebtAmountPlaceholder());
        assertEquals("E-mail для отправки чека", servicesPage.getEmailArrearsPlaceholder());
    }

    @Test
    @Order(2)
    public void testMobileServicesPaymentWithValidData() {
        var servicesPage = onlinePaymentPage.getPaymentServicesPage();
        var modalPage = onlinePaymentPage.getPaymentModalPage();

        servicesPage.selectMobileServices();
        servicesPage.enterMobilePhoneNumber(TestConfig.TEST_PHONE_NUMBER);
        servicesPage.enterMobileAmount(TestConfig.TEST_AMOUNT);

        assertTrue(servicesPage.isContinueButtonEnabled(),
                "Кнопка 'Продолжить' должна быть активна после заполнения полей");
        servicesPage.clickContinue();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            WebElement paymentIframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[@class=\"bepaid-iframe\"]")));

            driver.switchTo().frame(paymentIframe);
            System.out.println("Успешное переключение на iframe модального окна");
        } catch (TimeoutException e) {
            System.err.println("Не удалось найти iframe: " + e.getMessage());
            fail("Ошибка при переключении на iframe");
        }

        try {

            assertTrue(modalPage.isPaymentModalDisplayed(),
                    "Модальное окно оплаты не отображается");

            String actualPhone = modalPage.getPhoneNumberFromModal();
            String expectedPhone = TestConfig.TEST_PHONE_NUMBER.substring(3);
            assertEquals(expectedPhone, actualPhone,
                    "Номер телефона в модальном окне не совпадает с введенным. Ожидалось: " +
                            expectedPhone + ", но получено: " + actualPhone);

            String actualAmount = modalPage.getAmountFromModal();
            assertTrue(actualAmount.contains(TestConfig.TEST_AMOUNT),
                    "Сумма в модальном окне не совпадает с введенной. Ожидалось: " +
                            TestConfig.TEST_AMOUNT + ", но получено: " + actualAmount);

            String payButtonText = modalPage.getPayButtonAmount();
            assertTrue(payButtonText.contains(TestConfig.TEST_AMOUNT),
                    "Сумма на кнопке оплаты не совпадает с введенной. Ожидалось: " +
                            TestConfig.TEST_AMOUNT + ", но получено: " + payButtonText);

            assertEquals("Номер карты", modalPage.getCardNumberLabelText(),
                    "Неверная надпись для номера карты");
            assertEquals("Срок действия", modalPage.getExpiryDateLabelText(),
                    "Неверная надпись для срока действия карты");
            assertEquals("CVC", modalPage.getCvvLabelText(),
                    "Неверная надпись для CVC");
            assertEquals("Имя и фамилия на карте", modalPage.getCardholderNameLabelText(),
                    "Неверная надпись для имени владельца карты");

            assertTrue(modalPage.isVisaLogoDisplayed(), "Логотип Visa не отображается");
            assertTrue(modalPage.isMastercardLogoDisplayed(), "Логотип Mastercard не отображается");
            assertTrue(modalPage.isBelkartLogoDisplayed(), "Логотип Белкарт не отображается");

        } finally {
            driver.switchTo().defaultContent();
        }
    }


    @AfterEach
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Ошибка завершения теста: " + e.getMessage());
        }
    }
}