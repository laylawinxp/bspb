package org.example.tests.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.example.api.models.search.ContentSection;
import org.example.api.models.search.SearchResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class SearchApiTest extends BaseApiTest {
    private static final String SEARCH_PATH = "/search.json?slug=search";

    private String getBuildIdFromStaticLinks() {
        Response pageResponse = get("/");
        String html = pageResponse.asString();

        Pattern pattern = Pattern.compile("/_next/static/([^/]+)/_buildManifest\\.js");
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {
            return matcher.group(1);
        }

        Pattern ssgPattern = Pattern.compile("/_next/static/([^/]+)/_ssgManifest\\.js");
        Matcher ssgMatcher = ssgPattern.matcher(html);
        if (ssgMatcher.find()) {
            return ssgMatcher.group(1);
        }

        throw new IllegalStateException("Не удалось найти buildId в статических ссылках на странице /");
    }

    private String getEndpoint() {
        return "/_next/data/" + getBuildIdFromStaticLinks() + SEARCH_PATH;
    }

    @Test
    @Step("Проверка статус-кода эндпоинта поиска")
    public void testStatusCode() {
        Response response = get(getEndpoint());
        attachText("Status Code", String.valueOf(response.statusCode()));
        assertThat(response.statusCode())
                .as("Статус-код ответа API")
                .isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @Step("Проверка структуры ответа поиска")
    public void testStructure() {
        Response response = get(getEndpoint());
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
        Response response = get(getEndpoint());
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