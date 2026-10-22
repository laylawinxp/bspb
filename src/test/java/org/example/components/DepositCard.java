package org.example.components;

import org.example.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DepositCard extends BasePage {
    @FindBy(xpath = "//a[contains(@href, 'deposits') and text()='Оформить']")
    private WebElement depositButtonOpen;

    @FindBy(xpath = "//a[contains(@href, 'deposits') and text()='Подробнее']")
    private WebElement depositButtonMoreDetails;

    @FindBy(xpath = "//p[text()='Доход']/following-sibling::h2")
    private WebElement incomeField;

    @FindBy(xpath = "//p[text()='Ставка']/following-sibling::h2")
    private WebElement rateField;

    public DepositCard(WebDriver driver) {
        super(driver);
    }

    public boolean isButtonOpenEnabled() {
        wait.until(ExpectedConditions.visibilityOf(depositButtonOpen));
        return depositButtonOpen.isEnabled();
    }

    public boolean isButtonMoreDetailsEnabled() {
        wait.until(ExpectedConditions.visibilityOf(depositButtonMoreDetails));
        return depositButtonMoreDetails.isEnabled();
    }

    public String getIncomeFieldValue() {
        wait.until(ExpectedConditions.visibilityOf(incomeField));
        return incomeField.getText();
    }

    public String getRateFieldValue() {
        wait.until(ExpectedConditions.visibilityOf(rateField));
        return rateField.getText();
    }
}