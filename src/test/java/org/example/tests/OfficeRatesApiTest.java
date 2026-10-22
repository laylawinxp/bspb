package org.example.tests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.api.models.officeRates.Office;
import org.example.api.models.officeRates.OfficeRatesResponse;
import org.example.api.models.officeRates.Rate;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OfficeRatesApiTest extends BaseApiTest {
    private static final String ENDPOINT = "/api/currency-service/office-rates";

    @Test
    @Step("Проверка статус-кода эндпоинта курсов")
    public void testStatusCode() {
        Response response = get(ENDPOINT);
        attachText("Status Code", String.valueOf(response.statusCode()));
        assertThat(response.statusCode())
                .as("Статус-код ответа API")
                .isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @Step("Проверка структуры ответа и наличия отделений")
    public void testStructure() {
        Response response = get(ENDPOINT);
        OfficeRatesResponse officeResponse = response.as(OfficeRatesResponse.class);
        List<Office> offices = officeResponse.getItems();

        assertThat(offices)
                .as("Список отделений не должен быть пустым")
                .isNotNull()
                .isNotEmpty();

        Office firstOffice = offices.getFirst();
        attachObject("Пример отделения", firstOffice);

        assertThat(firstOffice)
                .as("Проверка данных первого отделения")
                .satisfies(office -> {
                    assertThat(office.getName()).as("Название").isNotBlank();
                    assertThat(office.getAddress()).as("Адрес").isNotBlank();
                });
    }

    @Test
    @Step("Проверка наличия курсов USD, CNY, AED или EUR в отделении")
    public void testContainsCurrencyRates() {
        Response response = get(ENDPOINT);
        OfficeRatesResponse officeResponse = response.as(OfficeRatesResponse.class);
        Office firstOffice = officeResponse.getItems().getFirst();

        assertThat(firstOffice.getRates()).as("Список курсов валют").isNotEmpty();

        attachText("Валюта", firstOffice.getRates().getFirst().getCurrencyCode());

        assertThat(firstOffice.getRates())
                .as("Должна быть хотя бы одна популярная валюта")
                .extracting(Rate::getCurrencyCode)
                .containsAnyOf("USD", "EUR", "CNY", "AED");

    }
}