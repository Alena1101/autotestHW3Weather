package com.alena;

import com.alena.models.Region;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class GetRegionListTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/v1/regions";
    }

    @Test
    public void getList_itemCount(){
        List<Region> regionList=given()
                .queryParam("apikey", getApiKey())
                
                .when()
                .get(getBaseURL()+getPath())
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Region.class);
        Assertions.assertEquals(10,regionList.size());


    }
}
