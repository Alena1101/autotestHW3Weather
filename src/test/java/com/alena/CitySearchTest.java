package com.alena;

import com.alena.models.City;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Epic(value = "Тестирование API http://dataservice.accuweather.com/")
@Feature(value = "Тестирование запроса locations/{version}/cities/search")
public class CitySearchTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/{version}/cities/search";
    }

    @Override
    protected Map<String, String> getQueryParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("q","A");
        return hashMap;
    }
    @DisplayName("Поиск города")
    @Description("Проверка количества вариантов при поиске по параметру")
    @Link("http://dataservice.accuweather.com/locations/v1/cities/search")
    @Severity(SeverityLevel.MINOR)
    @Owner("Ермоленко Елена")
    @Story(value = "Поиск")
    @ParameterizedTest
    @CsvSource({"a,7", "bel,13", "gfdgergre,0"})
    public void citySearch_itemCount(String query, int itemCount){
        List<City> cityList=given()
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1")
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
