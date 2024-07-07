package com.alena;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AccuweatherTest {
    private  String baseURL;
    private  String apiKey;
    private  Properties properties = new Properties();

    @BeforeAll
    public  void before() throws IOException {
        InputStream configFile = new FileInputStream("src\\test\\java\\resources\\accuweather.properties");
        properties.load(configFile);
        apiKey = properties.getProperty("apikey");
        baseURL = properties.getProperty("base_url");


    }
    public  String getApiKey(){
        return apiKey;
    }
    public  String getBaseURL(){
        return baseURL;
    }

    @Test
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
