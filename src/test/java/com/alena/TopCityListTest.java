package com.alena;

import com.alena.models.AdministrativeArea;
import com.alena.models.City;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;


public class TopCityListTest extends AccuweatherTest {
    private static final Logger logger = LoggerFactory.getLogger(TopCityListTest.class);
    @Override
    protected String getPath() {
        return "/locations/v1/topcities/{group}";
    }

    @Override
    protected Map<String, String> getPathParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("group","50");
        return hashMap;
    }
    @ParameterizedTest
    @ValueSource(ints = {50,100,150})
    public void getList_itemCount(int itemCount) throws JsonProcessingException {
        logger.info("Тест количество элементов запущен");
        //given
        ObjectMapper mapper = new ObjectMapper();
        List<City> bodyOk = new ArrayList<>();
        for (int i = 0; i < itemCount; i++){
            bodyOk.add(new City());
        }

        logger.debug("Формирование мока для GET "+ getPath());
        stubFor(get(urlPathTemplate(getPath()))
                .withQueryParam("apikey", equalTo(getApiKey()))
                .withPathParam("group",equalTo(String.valueOf(itemCount)))
                .willReturn(aResponse()
                        .withStatus(200).withBody(mapper.writeValueAsString(bodyOk))));
        logger.debug("http клиент создан");
        List<City> cityList=given()
                .queryParam("apikey", getApiKey())
                
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
