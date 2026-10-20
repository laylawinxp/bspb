package org.example.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationPanel {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public NavigationPanel(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickMenuItem(String menuItemName) {
        By locator = By.xpath(String.format("//a[contains(@href, 'retail')][.//p[text()='%s']]", menuItemName));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}