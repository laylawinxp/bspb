package org.example.tests.ui;

import org.example.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Description;

public class NavigationTest extends BaseTest {
    @Test
    public void testNavigationToCert() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCertificateDetails();

        assertThat(driver.getCurrentUrl())
                .as("После нажатия на кнопку 'Подробнее' должен открыться раздел о сертификатах")
                .contains("/retail/cert");
    }
}