package com.alena;

import java.util.HashMap;
import java.util.Map;

public class PostalCodeSearchWithOneParamTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/v1/postalcodes/{countryCode}/search";
    }

    @Override
    protected Map<String, String> getQueryParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("q","95640");
        return hashMap;
    }
    @Override
    protected Map<String, String> getPathParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("countryCode","US");
        return hashMap;
    }
}
