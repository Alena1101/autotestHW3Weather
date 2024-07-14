package com.alena;


import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AccuweatherTest extends AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(AccuweatherTest.class);

    private  String apiKey;
    private  Properties properties = new Properties();

    @BeforeAll
    public  void before() throws IOException {
        InputStream configFile = new FileInputStream("src\\test\\java\\resources\\accuweather.properties");
        properties.load(configFile);
        apiKey = properties.getProperty("apikey");




    }
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public  String getApiKey(){
        return apiKey;
    }


    @Test
    public void statusSuccess(){
        logger.info("Тест код ответ 200 запущен");

        logger.debug("Формирование мока для GET /locations/v1/cities/autocomplete");

        logger.debug("http клиент создан");

        RequestSpecification requestSpecification = given();
        requestSpecification
                .queryParam("apikey", getApiKey());

        getQueryParams().forEach(requestSpecification::queryParam);
        getPathParams().forEach(requestSpecification::pathParam);


        requestSpecification.when()
                .get(getBaseURL()+getPath())
                .then()
                .statusCode(200);


    }

    @Test
    public void statusError(){
        logger.info("Тест код ответ 401 запущен");

        logger.debug("Формирование мока для GET "+getPath());
        stubFor(get(urlPathEqualTo(getPath()))
                .withQueryParam("apiKey", notMatching(getApiKey()))
                .willReturn(aResponse()
                        .withStatus(401).withBody("ERROR")));

        logger.debug("http клиент создан");
        RequestSpecification requestSpecification = given();
        requestSpecification
                .queryParam("apikey", "");

        getQueryParams().forEach(requestSpecification::queryParam);
        getPathParams().forEach(requestSpecification::pathParam);


        requestSpecification.when()
                .get(getBaseURL()+getPath())
                .then()
                .statusCode(401);


    }
    protected abstract String getPath();
    protected  Map<String,String> getQueryParams(){
        return new HashMap<>();
    }
    protected  Map<String,String> getPathParams(){
        return new HashMap<>();
    }

}
