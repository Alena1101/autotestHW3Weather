package com.alena;

import com.alena.models.City;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Epic(value = "Тестирование API http://dataservice.accuweather.com/")
@Feature(value = "Тестирование запроса locations/{version}/topcities/{group}")
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
    @DisplayName("Список городов")
    @Description("Запрос должен возвращать указанное количество элементов в списке")
    @Link("http://dataservice.accuweather.com/locations/v1/topcities/100")
    @Severity(SeverityLevel.MINOR)
    @Owner("Ермоленко Елена")
    @Story(value = "Работа со списком")
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
