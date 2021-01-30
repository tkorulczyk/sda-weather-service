package com.wheaterservice.backend.httpclients;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wheaterservice.backend.LocationService;


import java.io.IOException;


public class AccuWeatherClient {
    private ObjectMapper objectMapper;
    private LocationService locationService;
    private HttpClientConnector httpClientConnector;
    private String APIkey = "b4GBr5Mm1Y9Z8yeCXDIFNK2LhJSsH573";
    private String timeRange = "daily/1day";
    private String cityName = "274231";
    private String language;
    private boolean details = true;
    private boolean metrics = false;


    public void setLanguage(String language) {
        this.language = language;
    }

    private String URL = String.format("http://dataservice.accuweather.com/forecasts/v1/%s/%s?apikey=%s&language=%s&details=%s&metric=%s", timeRange, cityName, APIkey, language, details, metrics);


    public AccuWeatherClient() {
    }

    public AccuWeatherClient(ObjectMapper objectMapper, LocationService locationService, HttpClientConnector httpClientConnector) {
        this.objectMapper = objectMapper;
        this.locationService = locationService;
        this.httpClientConnector = httpClientConnector;
    }

    public String getWeatherForecast() {


        String responseBody = httpClientConnector.initializeHttpConnection(URL);

        try {

            WheaterParamDTO wheaterParamDTO = objectMapper.readValue(responseBody, WheaterParamDTO.class);
            String temperature = wheaterParamDTO.getTemperature();
            System.out.println(responseBody);
            return temperature;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot initialize connection to AccuWeather");
            return null;

        }

    }

}
