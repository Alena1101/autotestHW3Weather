package com.alena;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import java.util.HashMap;
import java.util.Map;
@Epic(value = "Тестирование API http://dataservice.accuweather.com/")
@Feature(value = "Тестирование запроса locations/{version}/poi/{countryCode}/{adminCode}/search")
public class POISearchWithTwoParamsTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/{version}/poi/{countryCode}/{adminCode}/search";
    }

    @Override
    protected Map<String, String> getQueryParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("q","A");
        return hashMap;
    }
    @Override
    protected Map<String, String> getPathParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("countryCode","IS");
        hashMap.put("adminCode","8");
        return hashMap;
    }
}