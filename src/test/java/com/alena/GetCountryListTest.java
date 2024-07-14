package com.alena;

import com.alena.models.Country;
import com.alena.models.Region;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class GetCountryListTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/v1/countries/{region}";
    }

    @Override
    protected Map<String, String> getPathParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("region","AFR");
        return hashMap;
    }


    @Test
    public void getList_itemCount(){
        List<Country> countryList=given()
                .queryParam("apikey", getApiKey())
                
                .pathParam("region","AFR")
                .when()
                .get(getBaseURL()+getPath())
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
                
                .pathParam("region","AFR")
                .when()
                .get(getBaseURL()+getPath())
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Country.class);
        Country bj = countryList.stream().filter(country -> country.getId().equals("BJ")).findFirst().get();
        Assertions.assertEquals("Benin", bj.getEnglishName());


    }
}
