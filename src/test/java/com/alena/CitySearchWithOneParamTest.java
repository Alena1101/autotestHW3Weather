package com.alena;

import java.util.HashMap;
import java.util.Map;

public class CitySearchWithOneParamTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/{version}/cities/{countryCode}/search";
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
        return hashMap;
    }
}
