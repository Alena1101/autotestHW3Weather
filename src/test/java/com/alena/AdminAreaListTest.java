package com.alena;

import com.alena.models.AdministrativeArea;
import com.alena.models.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class AdminAreaListTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/v1/adminareas/{countryID}";
    }

    @Override
    protected Map<String, String> getPathParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("countryID","IN");
        return hashMap;
    }


    @ParameterizedTest
    @CsvSource({"IN,37", "PK,8", "pi,0"})
    public void getList_itemCount(String countryID, int itemCount){
        List<AdministrativeArea> administrativeAreaList=given()
                .queryParam("apikey", getApiKey())
                
                .pathParam("countryID",countryID)
                .when()
                .get(getBaseURL()+getPath())
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", AdministrativeArea.class);
        Assertions.assertEquals(itemCount,administrativeAreaList.size());


    }


}
