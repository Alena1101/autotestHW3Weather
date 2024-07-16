package com.alena;

import io.qameta.allure.*;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AccuweatherTest {
    private  String baseURL;
    private  String apiKey;


    @BeforeAll
    public  void before()  {

        apiKey = "ibU4M3nR7Bq5mquE5In0MPnFyU6YdkdX";
        baseURL = "http://dataservice.accuweather.com/";


    }
    public  String getApiKey(){
        return apiKey;
    }
    public  String getBaseURL(){
        return baseURL;
    }

    @Test
    @DisplayName("Статус код 200")
    @Description("Проверка, что запрос актуален и успешно выполняется")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Ермоленко Елена")
    @Story(value = "Успех")
    public void statusSuccess(){
        RequestSpecification requestSpecification = given();
        requestSpecification
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1");

        getQueryParams().forEach(requestSpecification::queryParam);
        getPathParams().forEach(requestSpecification::pathParam);


        requestSpecification.when()
                .get(getBaseURL()+getPath())
                .then()
                .statusCode(200);


    }
    @Test
    @DisplayName("Статус код 401")
    @Description("Проверка авторизации пользователя. Неавторизованный пользователь не должен иметь доступ к этим данным")
    @Link("")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Ермоленко Елена")
    @Story(value = "Запрос без авторизации")
    public void statusError(){
        RequestSpecification requestSpecification = given();
        requestSpecification
                .queryParam("apikey", "")
                .pathParam("version","v1");

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
