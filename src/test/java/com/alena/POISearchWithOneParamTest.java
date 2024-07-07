package com.alena;

import java.util.HashMap;
import java.util.Map;

public class POISearchWithOneParamTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/{version}/poi/{countryCode}/search";
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
        return hashMap;
    }
}