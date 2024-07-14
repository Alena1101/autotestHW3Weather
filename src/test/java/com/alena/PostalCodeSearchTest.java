package com.alena;

import java.util.HashMap;
import java.util.Map;

public class PostalCodeSearchTest extends AccuweatherTest {
    @Override
    protected String getPath() {
        return "locations/v1/postalcodes/search";
    }

    @Override
    protected Map<String, String> getQueryParams() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("q","95640");
        return hashMap;
    }


}
