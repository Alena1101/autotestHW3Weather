package com.alena;

import com.alena.models.Country;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Epic(value = "Тестирование API http://dataservice.accuweather.com/")
@Feature(value = "Тестирование запроса locations/{version}/countries/{region}")
public class GetCountryListTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/{version}/countries/{region}";
    }

    @Override
    protected Map<String, String> getPathParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("region","AFR");
        return hashMap;
    }



    @Test
    @DisplayName("Количество стран в регионе AFR")
    @Description("На данный момент в этом регионе 58 стран")
    @Link("http://dataservice.accuweather.com/locations/v1/countries/AFR")
    @Severity(SeverityLevel.MINOR)
    @Owner("Ермоленко Елена")
    @Story(value = "Работа со списком")
    public void getList_itemCount(){
        List<Country> countryList=given()
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1")
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
    @DisplayName("BJ = Benin")
    @Description("Проверка соответствия индекса с официальным названием страны в регионе AFR")
    @Link("http://dataservice.accuweather.com/locations/v1/countries/AFR")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Ермоленко Елена")
    @Story(value = "Работа со списком")
    public void getList_idCheck(){
        List<Country> countryList=given()
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1")
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
