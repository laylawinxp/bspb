package org.example.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.api.models.Office;
import org.example.api.models.OfficeRatesResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiTest {
    private static final String BSPB_URL = "https://www.bspb.ru";
    private static final String ENDPOINT_OFFICE_RATES = "/api/currency-service/office-rates";

    private Response getOfficeRatesResponse() {
        return given()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/155.0.0.0 Safari/537.36")
                .when()
                .get(ENDPOINT_OFFICE_RATES)
                .then()
                .extract().response();
    }

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = BSPB_URL;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void testOfficeRatesApiStatusCode() {
        Response response = getOfficeRatesResponse();

        assertThat(response.statusCode())
                .as("Статус-код ответа API должен быть 200")
                .isEqualTo(200);
    }

    @Test
    public void testOfficeRatesApiStructure() {
        Response response = getOfficeRatesResponse();

        OfficeRatesResponse officeResponse = response.as(OfficeRatesResponse.class);

        List<Office> offices = officeResponse.getItems();
        assertThat(offices)
                .as("Список отделений не должен быть пустым")
                .isNotNull()
                .isNotEmpty();

        Office firstOffice = offices.getFirst();

        assertThat(firstOffice.getName())
                .as("Название первого отделения не должно быть пустым")
                .isNotBlank();

        assertThat(firstOffice.getAddress())
                .as("Адрес первого отделения не должен быть пустым")
                .isNotBlank();
    }

    @Test
    public void testOfficeRatesApiContainsCurrencyRates() {
        Response response = getOfficeRatesResponse();
        OfficeRatesResponse officeResponse = response.as(OfficeRatesResponse.class);
        Office firstOffice = officeResponse.getItems().getFirst();

        assertThat(firstOffice.getRates())
                .as("Список курсов валют у отделения не должен быть пустым")
                .isNotEmpty();

        boolean hasUsd = firstOffice.getRates().stream()
                .anyMatch(rate -> "USD".equals(rate.getCurrencyCode()));
        boolean hasEur = firstOffice.getRates().stream()
                .anyMatch(rate -> "EUR".equals(rate.getCurrencyCode()));

        assertThat(hasUsd || hasEur)
                .as("В списке курса валют должна быть хотя бы USD или EUR")
                .isTrue();
    }
}