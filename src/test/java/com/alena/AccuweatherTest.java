package com.alena;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AccuweatherTest {
    private  String baseURL;
    private  String apiKey;
    private  Properties properties = new Properties();

    @BeforeAll
    public  void before() throws IOException {
        InputStream configFile = new FileInputStream("src\\test\\java\\resources\\accuweather.properties");
        properties.load(configFile);
        apiKey = properties.getProperty("apikey");
        baseURL = properties.getProperty("base_url");


    }
    public  String getApiKey(){
        return apiKey;
    }
    public  String getBaseURL(){
        return baseURL;
    }

}
