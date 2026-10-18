package org.example;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class NavigationSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    @Given("я на главной странице БСПБ")
    public void openMainPage() {
        driver = DriverFactory.createDriverAndOpenMainPage();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @When("я нажимаю на {string}")
    public void clickMenuItem(String menuItemName) {
        String xpath = String.format("//a[contains(@href, 'retail')][.//p[text()='%s']]", menuItemName);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
        wait.until(ExpectedConditions.urlContains("/retail"));
    }

    @Then("URL содержит {string}")
    public void urlContains(String urlPart) {
        assertThat(driver.getCurrentUrl()).contains(String.format("%s", urlPart));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
