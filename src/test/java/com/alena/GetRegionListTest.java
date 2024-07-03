package com.alena;

import com.alena.models.Region;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class GetRegionListTest extends AccuweatherTest {
    @Test
    public void getList_statusSuccess(){
        given()
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1")
                .when()
                .get(getBaseURL()+"locations/{version}/regions")
                .then()
                .statusCode(200);


    }
    @Test
    public void getList_statusError(){
        given()
                .queryParam("apikey", "")
                .pathParam("version","v1")
                .when()
                .get(getBaseURL()+"locations/{version}/regions")
                .then()
                .statusCode(401);


    }
    @Test
    public void getList_itemCount(){
        List<Region> regionList=given()
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1")
                .when()
                .get(getBaseURL()+"locations/{version}/regions")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Region.class);
        Assertions.assertEquals(10,regionList.size());


    }
}
