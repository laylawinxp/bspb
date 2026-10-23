package org.example.tests.ui;

import org.example.pages.MainPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsTest extends BaseTest {
    @Test
    public void testProductsAreVisible() {
        MainPage mainPage = new MainPage(driver);
        List<WebElement> products = mainPage.getProducts();

        assertThat(products).as("Список продуктов не должен быть пустым").isNotEmpty();
        assertThat(products).as("Должно быть ровно 6 продуктов").hasSize(6);
        assertThat(products).as("Все продукты должны быть видны").allMatch(WebElement::isDisplayed);
    }
}