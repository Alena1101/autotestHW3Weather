package com.alena;

import com.alena.models.AdministrativeArea;
import com.alena.models.Country;
import com.alena.models.Region;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;


public class GetCountryListTest extends AccuweatherTest {
    private static final Logger logger = LoggerFactory.getLogger(GetCountryListTest.class);
    @Override
    protected String getPath() {
        return "/locations/v1/countries/{region}";
    }

    @Override
    protected Map<String, String> getPathParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("region","AFR");
        return hashMap;
    }


    @Test
    public void getList_itemCount() throws JsonProcessingException {
        logger.info("Тест количество элементов запущен");
        int itemCount=58;
        //given
        ObjectMapper mapper = new ObjectMapper();
        List<Country> bodyOk = new ArrayList<>();
        for (int i = 0; i < itemCount; i++){
            bodyOk.add(new Country());
        }

        logger.debug("Формирование мока для GET "+ getPath());
        stubFor(get(urlPathTemplate(getPath()))
                .withQueryParam("apikey", equalTo(getApiKey()))
                .withPathParam("region",equalTo("AFR"))
                .willReturn(aResponse()
                        .withStatus(200).withBody(mapper.writeValueAsString(bodyOk))));
        logger.debug("http клиент создан");
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
        Assertions.assertEquals(itemCount,countryList.size());


    }
    @Test
    public void getList_idCheck() throws JsonProcessingException {
        logger.info("Тест проверки ID запущен");
        //given
        ObjectMapper mapper = new ObjectMapper();
        Country benin = new Country();
        benin.setId("BJ");
        benin.setEnglishName("Benin");
        Country belarus = new Country();
        benin.setId("BY");
        benin.setEnglishName("Belarus");
        Country france = new Country();
        benin.setId("FR");
        benin.setEnglishName("France");
        List<Country> bodyOk = List.of(benin,belarus,france);

        logger.debug("Формирование мока для GET "+ getPath());
        stubFor(get(urlPathTemplate(getPath()))
                .withQueryParam("apikey", equalTo(getApiKey()))
                .withPathParam("region",equalTo("AFR"))
                .willReturn(aResponse()
                        .withStatus(200).withBody(mapper.writeValueAsString(bodyOk))));
        logger.debug("http клиент создан");
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


        Country bj = countryList.stream().filter(country -> Objects.equals(country.getId(),"FR")).findFirst().get();
        Assertions.assertEquals("France", bj.getEnglishName());


    }
}
