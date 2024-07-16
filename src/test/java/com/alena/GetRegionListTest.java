package com.alena;

import com.alena.models.Region;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic(value = "Тестирование API http://dataservice.accuweather.com/")
@Feature(value = "Тестирование запроса locations/{version}/regions")
public class GetRegionListTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/{version}/regions";
    }

    @Test
    @DisplayName("Список регионов")
    @Description("Проверка количества регионов")
    @Link("http://dataservice.accuweather.com/locations/v1/regions")
    @Severity(SeverityLevel.MINOR)
    @Owner("Ермоленко Елена")
    @Story(value = "Работа со списком")
    public void getList_itemCount(){
        List<Region> regionList=given()
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1")
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
