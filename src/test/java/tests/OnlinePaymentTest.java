package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
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
    private WebDriverWait wait;
    private OnlinePaymentPage onlinePaymentPage;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        onlinePaymentPage = new OnlinePaymentPage(driver);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        onlinePaymentPage.navigateToPaymentPage();
        onlinePaymentPage.openPaymentsSection();
    }

    @Test
    @Order(1)
    public void testBlockTitlePresence() {
        String title = onlinePaymentPage.getBlockTitle();
        assertNotNull(title, "Заголовок блока не найден");

        String normalizedTitle = title.replace("\n", " ").replace("\r", " ").trim().replaceAll("\\s+", " ");
        String expectedTitle = "Онлайн пополнение без комиссии";

        assertEquals(expectedTitle, normalizedTitle,
                "Название блока должно быть 'Онлайн пополнение без комиссии'. Получено: " + title);
    }

    @Test
    @Order(2)
    public void testPaymentSystemLogosPresence() {
        int logosCount = onlinePaymentPage.getPaymentSystemLogosCount();
        assertTrue(logosCount >= 3,
                "Должно быть не менее 3 логотипов платежных систем. Найдено: " + logosCount);

        boolean allLogosDisplayed = onlinePaymentPage.arePaymentLogosDisplayed();
        assertTrue(allLogosDisplayed, "Не все логотипы платежных систем отображаются");

        assertTrue(logosCount <= 10,
                "Слишком много логотипов (" + logosCount + "), возможно ошибка в локаторе");
    }

    @Test
    @Order(3)
    public void testDetailsLinkClickability() {

        WebElement detailsLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Подробнее о сервисе")));
        detailsLink.click();
        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));
        Assertions.assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", driver.getCurrentUrl());
    }

    @Test
    @Order(4)
    public void testContinueButtonWithValidData() {
        assertDoesNotThrow(() -> {
            onlinePaymentPage.enterPhoneNumber(TestConfig.TEST_PHONE_NUMBER);
            onlinePaymentPage.enterAmount("1");

            assertTrue(onlinePaymentPage.isContinueButtonEnabled(), "Кнопка 'Продолжить' должна быть активна после заполнения полей");
        });
    }

    @AfterEach
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Ошибка завершения теста: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
