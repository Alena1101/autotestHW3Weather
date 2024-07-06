package com.alena;

import com.alena.models.AdministrativeArea;
import com.alena.models.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static io.restassured.RestAssured.given;


public class AdminAreaListTest extends AccuweatherTest {
    @Test
    public void getList_statusSuccess(){
        given()
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1")
                .pathParam("countryID","IN")
                .when()
                .get(getBaseURL()+"locations/{version}/adminareas/{countryID}")
                .then()
                .statusCode(200);


    }
    @Test
    public void getList_statusError(){
        given()
                .queryParam("apikey", "")
                .pathParam("version","v1")
                .pathParam("countryID","IN")
                .when()
                .get(getBaseURL()+"locations/{version}/adminareas/{countryID}")
                .then()
                .statusCode(401);


    }
    @ParameterizedTest
    @CsvSource({"IN,37", "PK,8", "pi,0"})
    public void getList_itemCount(String countryID, int itemCount){
        List<AdministrativeArea> administrativeAreaList=given()
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1")
                .pathParam("countryID",countryID)
                .when()
                .get(getBaseURL()+"locations/{version}/adminareas/{countryID}")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", AdministrativeArea.class);
        Assertions.assertEquals(itemCount,administrativeAreaList.size());


    }

}
