package com.alena;

import com.alena.models.AdministrativeArea;
import com.alena.models.Region;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;


public class GetRegionListTest extends AccuweatherTest {
    private static final Logger logger = LoggerFactory.getLogger(GetRegionListTest.class);
    @Override
    protected String getPath() {
        return "/locations/v1/regions";
    }

    @Test
    public void getList_itemCount() throws JsonProcessingException {
        int itemCount = 10;
        logger.info("Тест количество элементов запущен");
        //given
        ObjectMapper mapper = new ObjectMapper();
        List<Region> bodyOk = new ArrayList<>();
        for (int i = 0; i < itemCount; i++){
            bodyOk.add(new Region());
        }

        logger.debug("Формирование мока для GET "+ getPath());
        stubFor(get(urlPathEqualTo(getPath()))
                .withQueryParam("apikey", equalTo(getApiKey()))
                .willReturn(aResponse()
                        .withStatus(200).withBody(mapper.writeValueAsString(bodyOk))));
        logger.debug("http клиент создан");
        List<Region> regionList=given()
                .queryParam("apikey", getApiKey())
                
                .when()
                .get(getBaseURL()+getPath())
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Region.class);
        Assertions.assertEquals(itemCount,regionList.size());


    }
}
