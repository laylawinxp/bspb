package org.example.pages;

import org.example.components.DepositCard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage extends BasePage {
    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement signInButton;

    @FindBy(xpath = "//span[text()='Интернет-банк ФЛ']")
    private WebElement signInToInternetBank;

    @FindBy(xpath = "//h2[text()='Выберите свой продукт']/parent::div//a[starts-with(@href, '/retail/')]")
    private List<WebElement> products;


    @FindBy(xpath = "//a[@href='/retail/cert'][.//span[text()='Подробнее']]")
    private WebElement certificateDetailsButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSignInButtonVisible() {
        wait.until(ExpectedConditions.visibilityOf(signInButton));
        return signInButton.isDisplayed();
    }

    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public void clickSignInToInternetBank() {
        wait.until(ExpectedConditions.elementToBeClickable(signInToInternetBank)).click();
    }

    public SignInPage clickSignInToInternetBankAndSwitch() {
        wait.until(ExpectedConditions.elementToBeClickable(signInToInternetBank)).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Object[] handles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) handles[handles.length - 1]);
        return new SignInPage(driver);
    }

    public List<WebElement> getProducts() {
        wait.until(ExpectedConditions.visibilityOfAllElements(products));
        return products;
    }

    public void clickCertificateDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(certificateDetailsButton)).click();
        wait.until(ExpectedConditions.urlContains("/retail/cert"));
    }

    public DepositCard getDepositCard() {
        return new DepositCard(driver);
    }
}
