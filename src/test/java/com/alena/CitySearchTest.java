package com.alena;

import com.alena.models.City;
import com.alena.models.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static io.restassured.RestAssured.given;


public class CitySearchTest extends AccuweatherTest {
    @Test
    public void citySearch_statusSuccess(){
        given()
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1")
                .queryParam("q","A")
                .when()
                .get(getBaseURL()+"locations/{version}/cities/search")
                .then()
                .statusCode(200);


    }
    @Test
    public void citySearch_statusError(){
        given()
                .queryParam("apikey", "")
                .pathParam("version","v1")
                .queryParam("q","A")
                .when()
                .get(getBaseURL()+"locations/{version}/cities/search")
                .then()
                .statusCode(401);


    }
    @ParameterizedTest
    @CsvSource({"a,7", "bel,13", "gfdgergre,0"})
    public void citySearch_itemCount(String query, int itemCount){
        List<City> cityList=given()
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1")
                .queryParam("q",query)
                .when()
                .get(getBaseURL()+"locations/{version}/cities/search")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", City.class);
        Assertions.assertEquals(itemCount,cityList.size());


    }

}
