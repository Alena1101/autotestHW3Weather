package com.alena;

import com.alena.models.AdministrativeArea;
import com.alena.models.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class TopCityListTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/{version}/topcities/{group}";
    }

    @Override
    protected Map<String, String> getPathParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("group","50");
        return hashMap;
    }
    @ParameterizedTest
    @ValueSource(ints = {50,100,150})
    public void getList_itemCount(int itemCount){
        List<City> cityList=given()
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1")
                .pathParam("group",itemCount)
                .when()
                .get(getBaseURL()+getPath())
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", City.class);
        Assertions.assertEquals(itemCount,cityList.size());


    }


}
