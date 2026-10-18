package org.example;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.createDriverAndOpenMainPage();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBspbPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Войти']")));
        String title = driver.getTitle();
        Assertions.assertTrue(title.contains("БСПБ"), "Заголовок не содержит 'БСПБ':" + title);
    }

    @Test
    public void testSignInFormIsVisible() {
        driver.findElement(By.xpath("//button[text()='Войти']")).click();
        driver.findElement(By.xpath("//span[text()='Интернет-банк ФЛ']")).click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[windowHandles.length - 1]);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phoneNumber")));

        assertThat(driver.findElement(By.id("phoneNumber")).isDisplayed()).isTrue();
        assertThat(driver.findElement(By.id("password")).isDisplayed()).isTrue();
        assertThat(driver.findElement(By.xpath("//*[text()='Продолжить']")).isEnabled()).isTrue();
    }

    @Test
    public void testProductsAreVisible() {
        WebElement productsGrid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Выберите свой продукт']/parent::div")));
        List<WebElement> products = productsGrid.findElements(By.cssSelector("a[href^='/retail/']"));

        assertThat(products)
                .isNotEmpty()
                .hasSize(6)
                .allMatch(WebElement::isDisplayed);
    }

    @Test
    public void testNavigationToCert() {
        driver.findElement(By.xpath("//a[@href='/retail/cert'][.//span[text()='Подробнее']]")).click();

        wait.until(ExpectedConditions.urlContains("/cert"));

        assertThat(driver.getCurrentUrl()).contains("/retail/cert");
    }

    @Test
    public void testDepositCalculatorDisplaysValidData() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Доход']")));

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(driver.findElement(By.xpath("//a[contains(@href, 'deposits') and text()='Оформить']")).isEnabled()).isTrue();
            softly.assertThat(driver.findElement(By.xpath("//a[contains(@href, 'deposits') and text()='Подробнее']")).isEnabled()).isTrue();
            softly.assertThat(driver.findElement(By.xpath("//p[text()='Доход']/following-sibling::h2")).getText()).matches("\\d{1,3}( \\d{3})* ₽");
            softly.assertThat(driver.findElement(By.xpath("//p[text()='Ставка']/following-sibling::h2")).getText()).matches("\\d{1,2}([.]\\d{1,2})? %");
        });
    }
}