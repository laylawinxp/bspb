package org.example.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public abstract class BaseApiTest {
    protected static final String BSPB_URL = "https://www.bspb.ru";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = BSPB_URL;
        RestAssured.useRelaxedHTTPSValidation();
    }

    protected Response get(String endpoint) {
        return given()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/155.0.0.0 Safari/537.36")
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }

    @Attachment(value = "{name}", type = "text/plain")
    public String attachText(String name, String text) {
        return text;
    }

    @Attachment(value = "{name}", type = "application/json")
    public String attachJson(String name, String json) {
        return json;
    }

    @Attachment(value = "{name}", type = "application/json")
    public String attachObject(String name, Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            System.err.println("Не удалось прикрепить вложение для Allure Report: " + e.getMessage());
            return "Ошибка сериализации: " + e.getMessage();
        }
    }
}