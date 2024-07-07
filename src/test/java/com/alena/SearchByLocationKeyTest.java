package com.alena;

import java.util.HashMap;
import java.util.Map;

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

