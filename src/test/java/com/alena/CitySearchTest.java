package com.alena;

import com.alena.models.City;
import com.alena.models.Country;
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


public class CitySearchTest extends AccuweatherTest {
    private static final Logger logger = LoggerFactory.getLogger(CitySearchTest.class);
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
    public void citySearch_itemCount(String query, int itemCount) throws JsonProcessingException {
        logger.info("Тест количество элементов запущен");
        //given
        ObjectMapper mapper = new ObjectMapper();
        List<City> bodyOk = new ArrayList<>();
        for (int i = 0; i < itemCount; i++){
            bodyOk.add(new City());
        }

        logger.debug("Формирование мока для GET "+ getPath());
        stubFor(get(urlPathEqualTo(getPath()))
                .withQueryParam("apikey", equalTo(getApiKey()))
                .withQueryParam("q", equalTo(query))
                .willReturn(aResponse()
                        .withStatus(200).withBody(mapper.writeValueAsString(bodyOk))));
        logger.debug("http клиент создан");

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
