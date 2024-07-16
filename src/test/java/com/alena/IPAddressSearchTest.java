package com.alena;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import java.util.HashMap;
import java.util.Map;
@Epic(value = "Тестирование API http://dataservice.accuweather.com/")
@Feature(value = "Тестирование запроса locations/{version}/cities/ipaddress")
public class IPAddressSearchTest extends AccuweatherTest {

    @Override
    protected String getPath() {
        return "locations/{version}/cities/ipaddress";
    }
    @Override
    protected Map<String, String> getQueryParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("q","46.53.245.145");
        return hashMap;
    }
}
