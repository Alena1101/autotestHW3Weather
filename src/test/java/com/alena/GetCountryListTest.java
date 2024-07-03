package com.alena;

import com.alena.models.Country;
import com.alena.models.Region;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class GetCountryListTest extends AccuweatherTest {
    @Test
    public void getList_statusSuccess(){
        given()
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1")
                .pathParam("region","AFR")
                .when()
                .get(getBaseURL()+"locations/{version}/countries/{region}")
                .then()
                .statusCode(200);


    }
    @Test
    public void getList_statusError(){
        given()
                .queryParam("apikey", "")
                .pathParam("version","v1")
                .pathParam("region","AFR")
                .when()
                .get(getBaseURL()+"locations/{version}/countries/{region}")
                .then()
                .statusCode(401);


    }
    @Test
    public void getList_itemCount(){
        List<Country> countryList=given()
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1")
                .pathParam("region","AFR")
                .when()
                .get(getBaseURL()+"locations/{version}/countries/{region}")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Country.class);
        Assertions.assertEquals(58,countryList.size());


    }
    @Test
    public void getList_idCheck(){
        List<Country> countryList=given()
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1")
                .pathParam("region","AFR")
                .when()
                .get(getBaseURL()+"locations/{version}/countries/{region}")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Country.class);
        Country bj = countryList.stream().filter(country -> country.getId().equals("BJ")).findFirst().get();
        Assertions.assertEquals("Benin", bj.getEnglishName());


    }
}
