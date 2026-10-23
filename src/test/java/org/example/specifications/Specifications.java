package org.example.specifications;

import org.example.config.Config;
import org.springframework.http.HttpStatus;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Config.BASE_URL)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .setConfig(RestAssuredConfig.config()
                        .logConfig(LogConfig.logConfig()
                                .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .build();
    }
}