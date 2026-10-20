package org.example.tests;

import org.example.pages.MainPage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PageLoadTest extends BaseTest {
    @Test
    public void testBspbPageLoad() {
        MainPage mainPage = new MainPage(driver);
        assertThat(mainPage.isSignInButtonVisible())
                .as("Кнопка 'Войти' должна быть видна на главной странице")
                .isTrue();

        assertThat(driver.getTitle())
                .as("Заголовок страницы должен содержать 'БСПБ'")
                .contains("БСПБ");
    }
}