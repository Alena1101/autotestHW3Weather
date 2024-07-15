package com.alena;

import com.alena.models.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class GeoPositionSearchTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "/locations/v1/cities/geoposition/search";
    }

    @Override
    protected Map<String, String> getQueryParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("q","53.762, -7.641");
        return hashMap;
    }


}
