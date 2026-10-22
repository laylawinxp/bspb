package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class SignInPage extends BasePage {
    @FindBy(id = "phoneNumber")
    private WebElement signInFormItemPhoneNumber;

    @FindBy(id = "password")
    private WebElement signInFormItemPassword;

    @FindBy(xpath = "//*[text()='Продолжить']")
    private WebElement signInFormButtonContinue;

    public SignInPage(WebDriver driver) {
        super(driver, Duration.ofSeconds(30));
    }

    public boolean isPhoneNumberDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(signInFormItemPhoneNumber));
        return signInFormItemPhoneNumber.isDisplayed();
    }

    public boolean isPasswordDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(signInFormItemPassword));
        return signInFormItemPassword.isDisplayed();
    }
}