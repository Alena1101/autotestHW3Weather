package com.alena;

import com.alena.models.AdministrativeArea;
import com.alena.models.City;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;


public class AdminAreaListTest extends AccuweatherTest {
    private static final Logger logger = LoggerFactory.getLogger(AdminAreaListTest.class);
    @Override
    protected String getPath() {
        return "/locations/v1/adminareas/{countryID}";
    }

    @Override
    protected Map<String, String> getPathParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("countryID","IN");
        return hashMap;
    }


    @ParameterizedTest
    @CsvSource({"IN,37", "PK,8", "pi,0"})
    public void getList_itemCount(String countryID, int itemCount) throws JsonProcessingException {
        logger.info("Тест количество элементов запущен");
        //given
        ObjectMapper mapper = new ObjectMapper();
        List<AdministrativeArea> bodyOk = new ArrayList<>();
        for (int i = 0; i < itemCount; i++){
            bodyOk.add(new AdministrativeArea());
        }

        logger.debug("Формирование мока для GET "+ getPath());
        stubFor(get(urlPathTemplate(getPath()))
                .withQueryParam("apikey", equalTo(getApiKey()))
                .withPathParam("countryID",equalTo(countryID))
                .willReturn(aResponse()
                        .withStatus(200).withBody(mapper.writeValueAsString(bodyOk))));
        logger.debug("http клиент создан");
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
