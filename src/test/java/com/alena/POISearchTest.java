package com.alena;

import java.util.HashMap;
import java.util.Map;

public class POISearchTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/v1/poi/search";
    }

    @Override
    protected Map<String, String> getQueryParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("q","A");
        return hashMap;
    }


}