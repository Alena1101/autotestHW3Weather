package com.alena;

import com.alena.models.AdministrativeArea;
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
@Feature(value = "Тестирование запроса locations/{version}/adminareas/{countryID}")
public class AdminAreaListTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/{version}/adminareas/{countryID}";
    }

    @Override
    protected Map<String, String> getPathParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("countryID","IN");
        return hashMap;
    }


    @ParameterizedTest
    @CsvSource({"IN,37", "PK,8", "pi,0"})
    @DisplayName("Количество областей в стране")
    @Description("Проверка количества ообластей в разных странах")
    @Link("http://dataservice.accuweather.com/locations/v1/adminareas/IN")
    @Severity(SeverityLevel.MINOR)
    @Owner("Ермоленко Елена")
    @Story(value = "Работа со списком")
    public void getList_itemCount(String countryID, int itemCount){
        List<AdministrativeArea> administrativeAreaList=given()
                .queryParam("apikey", getApiKey())
                .pathParam("version","v1")
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
