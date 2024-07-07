package com.alena;

import java.util.HashMap;
import java.util.Map;

public class CitySearchWithTwoParamsTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/{version}/cities/{countryCode}/{adminCode}/search";
    }

    @Override
    protected Map<String, String> getQueryParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("q","Addis Ababa");
        return hashMap;
    }
    @Override
    protected Map<String, String> getPathParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("countryCode","ET");
        hashMap.put("adminCode","AA");
        return hashMap;
    }
}
