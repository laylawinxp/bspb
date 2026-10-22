package org.example.tests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.api.models.search.ContentSection;
import org.example.api.models.search.SearchResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchApiTest extends BaseApiTest {
    private static final String ENDPOINT = "/_next/data/hPR1lDeqHWLwL_1OcQMGW/search.json?slug=search";

    @Test
    @Step("Проверка статус-кода эндпоинта поиска")
    public void testStatusCode() {
        Response response = get(ENDPOINT);
        attachText("Status Code", String.valueOf(response.statusCode()));
        assertThat(response.statusCode())
                .as("Статус-код ответа API")
                .isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @Step("Проверка структуры ответа поиска")
    public void testStructure() {
        Response response = get(ENDPOINT);
        SearchResponse searchResponse = response.as(SearchResponse.class);

        assertThat(searchResponse.getPageProps())
                .as("Блок pageProps")
                .isNotNull();

        assertThat(searchResponse.getPageProps().getMetadata().getMetaTitle())
                .as("Заголовок страницы")
                .isNotBlank()
                .contains("Поиск");

        assertThat(searchResponse.getPageProps().getCollectionData().getContentSections())
                .as("Список секций")
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    @Step("Проверка секции поиска")
    public void testSearchSection() {
        Response response = get(ENDPOINT);
        SearchResponse searchResponse = response.as(SearchResponse.class);

        List<ContentSection> sections = searchResponse.getPageProps()
                .getCollectionData()
                .getContentSections();

        ContentSection firstSection = sections.getFirst();
        attachObject("Первая секция", firstSection);

        assertThat(firstSection.getComponent())
                .as("Компонент первой секции")
                .isNotBlank();

        assertThat(firstSection.getId())
                .as("ID первой секции")
                .isGreaterThan(0);
    }
}