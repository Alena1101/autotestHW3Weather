package com.alena;

import com.alena.models.City;
import com.alena.models.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class CitySearchTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "/locations/v1/cities/search";
    }

    @Override
    protected Map<String, String> getQueryParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("q","A");
        return hashMap;
    }

    @ParameterizedTest
    @CsvSource({"a,7", "bel,13", "gfdgergre,0"})
    public void citySearch_itemCount(String query, int itemCount){
        List<City> cityList=given()
                .queryParam("apikey", getApiKey())
                .queryParam("q",query)
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
