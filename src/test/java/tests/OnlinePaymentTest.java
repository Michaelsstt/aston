package tests;

import io.qameta.allure.*;
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

@Epic("Онлайн оплата услуг")
@Feature("Проверка функциональности онлайн оплаты")
@Story("Тестирование различных сервисов оплаты")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OnlinePaymentTest {

    private WebDriver driver;
    private OnlinePaymentPage onlinePaymentPage;

    @BeforeEach
    @Step("Инициализация драйвера и переход на страницу оплаты")
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        onlinePaymentPage = new OnlinePaymentPage(driver);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        onlinePaymentPage.navigateToPaymentPage();

        Allure.addAttachment("Browser Info", "text/plain",
                "Browser: " + driver.getClass().getSimpleName());
    }

    @Test
    @Order(1)
    @DisplayName("Проверка плейсхолдеров полей для всех сервисов")
    @Description("Тест проверяет корректность плейсхолдеров для различных сервисов оплаты")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("regression")
    @Tag("ui")
    public void testEmptyFieldPlaceholdersForAllServices() {
        var servicesPage = onlinePaymentPage.getPaymentServicesPage();

        executeStep("Проверка мобильных сервисов", () -> {
            servicesPage.selectMobileServices();
            assertEquals("Номер телефона", servicesPage.getMobilePhonePlaceholder());
            assertEquals("Сумма", servicesPage.getMobileAmountPlaceholder());
            assertEquals("E-mail для отправки чека", servicesPage.getEmailPlaceholder());
        });

        executeStep("Проверка интернет сервисов", () -> {
            servicesPage.selectInternetServices();
            assertEquals("Номер абонента", servicesPage.getInternetPhonePlaceholder());
            assertEquals("Сумма", servicesPage.getInternetAmountPlaceholder());
            assertEquals("E-mail для отправки чека", servicesPage.getInternetEmailInput());
        });

        executeStep("Проверка рассрочки", () -> {
            servicesPage.selectInstallment();
            assertEquals("Номер счета на 44", servicesPage.getInstallmentAccountPlaceholder());
            assertEquals("Сумма", servicesPage.getInstallmentAmountPlaceholder());
            assertEquals("E-mail для отправки чека", servicesPage.getEmailInstalmentPlaceholder());
        });

        executeStep("Проверка задолженности", () -> {
            servicesPage.selectDebt();
            assertEquals("Номер счета на 2073", servicesPage.getDebtAccountPlaceholder());
            assertEquals("Сумма", servicesPage.getDebtAmountPlaceholder());
            assertEquals("E-mail для отправки чека", servicesPage.getEmailArrearsPlaceholder());
        });
    }

    @Test
    @Order(2)
    @DisplayName("Оплата мобильных услуг с валидными данными")
    @Description("Тест проверяет процесс оплаты мобильных услуг с корректными данными")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("smoke")
    @Tag("payment")
    public void testMobileServicesPaymentWithValidData() {
        var servicesPage = onlinePaymentPage.getPaymentServicesPage();
        var modalPage = onlinePaymentPage.getPaymentModalPage();

        executeStep("Выбор мобильных сервисов и заполнение данных", () -> {
            servicesPage.selectMobileServices();
            servicesPage.enterMobilePhoneNumber(TestConfig.TEST_PHONE_NUMBER);
            servicesPage.enterMobileAmount(TestConfig.TEST_AMOUNT);

            Allure.addAttachment("Введенные данные", "text/plain",
                    "Телефон: " + TestConfig.TEST_PHONE_NUMBER + "\nСумма: " + TestConfig.TEST_AMOUNT);
        });

        executeStep("Проверка активности кнопки продолжения", () -> {
            assertTrue(servicesPage.isContinueButtonEnabled(),
                    "Кнопка 'Продолжить' должна быть активна после заполнения полей");
            servicesPage.clickContinue();
        });

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        executeStep("Переключение на iframe платежного модального окна", () -> {
            try {
                WebElement paymentIframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[@class=\"bepaid-iframe\"]")));

                driver.switchTo().frame(paymentIframe);
                Allure.addAttachment("Iframe Info", "text/plain", "Успешное переключение на iframe");
            } catch (TimeoutException e) {
                Allure.addAttachment("Iframe Error", "text/plain",
                        "Не удалось найти iframe: " + e.getMessage());
                fail("Ошибка при переключении на iframe");
            }
        });

        try {
            executeStep("Проверка отображения модального окна оплаты", () -> {
                assertTrue(modalPage.isPaymentModalDisplayed(),
                        "Модальное окно оплаты не отображается");
            });

            executeStep("Проверка номера телефона в модальном окне", () -> {
                String actualPhone = modalPage.getPhoneNumberFromModal();
                String expectedPhone = TestConfig.TEST_PHONE_NUMBER.substring(3);
                assertEquals(expectedPhone, actualPhone,
                        "Номер телефона в модальном окне не совпадает с введенным");

                Allure.addAttachment("Номер телефона", "text/plain",
                        "Ожидалось: " + expectedPhone + "\nПолучено: " + actualPhone);
            });

            executeStep("Проверка суммы в модальном окне", () -> {
                String actualAmount = modalPage.getAmountFromModal();
                assertTrue(actualAmount.contains(TestConfig.TEST_AMOUNT),
                        "Сумма в модальном окне не совпадает с введенной");

                Allure.addAttachment("Сумма оплаты", "text/plain",
                        "Ожидалось: " + TestConfig.TEST_AMOUNT + "\nПолучено: " + actualAmount);
            });

            executeStep("Проверка кнопки оплаты", () -> {
                String payButtonText = modalPage.getPayButtonAmount();
                assertTrue(payButtonText.contains(TestConfig.TEST_AMOUNT),
                        "Сумма на кнопке оплаты не совпадает с введенной");
            });

            executeStep("Проверка лейблов полей ввода", () -> {
                assertEquals("Номер карты", modalPage.getCardNumberLabelText(),
                        "Неверная надпись для номера карты");
                assertEquals("Срок действия", modalPage.getExpiryDateLabelText(),
                        "Неверная надпись для срока действия карты");
                assertEquals("CVC", modalPage.getCvvLabelText(),
                        "Неверная надпись для CVC");
                assertEquals("Имя и фамилия на карте", modalPage.getCardholderNameLabelText(),
                        "Неверная надпись для имени владельца карты");
            });

            executeStep("Проверка логотипов платежных систем", () -> {
                assertTrue(modalPage.isVisaLogoDisplayed(), "Логотип Visa не отображается");
                assertTrue(modalPage.isMastercardLogoDisplayed(), "Логотип Mastercard не отображается");
                assertTrue(modalPage.isBelkartLogoDisplayed(), "Логотип Белкарт не отображается");

                Allure.addAttachment("Логотипы", "text/plain",
                        "Visa: " + modalPage.isVisaLogoDisplayed() +
                                "\nMastercard: " + modalPage.isMastercardLogoDisplayed() +
                                "\nBelkart: " + modalPage.isBelkartLogoDisplayed());
            });

        } finally {
            driver.switchTo().defaultContent();
        }
    }


    @AfterEach
    @Step("Завершение теста и закрытие драйвера")
    public void tearDown() {
        try {
            if (driver != null) {
                // Сделать скриншот перед закрытием
                byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
                Allure.getLifecycle().addAttachment("Final Screenshot", "image/png", ".png", screenshot);

                driver.quit();
                Allure.addAttachment("Browser Status", "text/plain", "Браузер успешно закрыт");
            }
        } catch (Exception e) {
            Allure.addAttachment("TearDown Error", "text/plain",
                    "Ошибка завершения теста: " + e.getMessage());
            System.err.println("Ошибка завершения теста: " + e.getMessage());
        }
    }

    private void executeStep(String stepName, Runnable action) {
        try {
            Allure.step(stepName, action::run);
        } catch (Exception e) {
            Allure.addAttachment("Step Error", "text/plain",
                    "Ошибка в шаге '" + stepName + "': " + e.getMessage());
            throw e;
        }
    }
}