package com.alena;

import java.util.HashMap;
import java.util.Map;

public class TextSearchWithTwoParamsTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/v1/{countryCode}/{adminCode}/search";
    }

    @Override
    protected Map<String, String> getQueryParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("q","France");
        return hashMap;
    }
    @Override
    protected Map<String, String> getPathParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("countryCode","IE");
        hashMap.put("adminCode","LD");
        return hashMap;
    }
}
