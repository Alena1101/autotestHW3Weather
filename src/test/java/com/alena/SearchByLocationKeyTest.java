package com.alena;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import java.util.HashMap;
import java.util.Map;
@Epic(value = "Тестирование API http://dataservice.accuweather.com/")
@Feature(value = "Тестирование запроса locations/{version}/{locationKey}")
public class SearchByLocationKeyTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/{version}/{locationKey}";
    }

    @Override
    protected Map<String, String> getPathParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("locationKey","182536");
        return hashMap;
    }



}

