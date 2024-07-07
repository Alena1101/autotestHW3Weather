package com.alena;

import java.util.HashMap;
import java.util.Map;

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
