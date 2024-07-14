package com.alena;

import java.util.HashMap;
import java.util.Map;

public class CityNeighborsTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/v1/cities/neighbors/{locationKey}";
    }

    @Override
    protected Map<String, String> getPathParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("locationKey","182536");
        return hashMap;
    }



}
