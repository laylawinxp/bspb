package org.example.tests;

import org.example.pages.MainPage;
import org.example.pages.SignInPage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SignInTest extends BaseTest {
    @Test
    public void testSignInFormIsVisible() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSignInButton();
        SignInPage signInPage = mainPage.clickSignInToInternetBankAndSwitch();

        assertThat(signInPage.isPhoneNumberDisplayed())
                .as("Поле ввода номера телефона должно отображаться")
                .isTrue();

        assertThat(signInPage.isPasswordDisplayed())
                .as("Поле ввода пароля должно отображаться")
                .isTrue();
    }
}