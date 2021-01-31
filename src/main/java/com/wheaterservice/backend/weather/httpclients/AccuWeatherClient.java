package com.wheaterservice.backend.weather.httpclients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wheaterservice.backend.location.LocationService;
import com.wheaterservice.backend.weather.WeatherService;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


public class AccuWeatherClient {
    private ObjectMapper objectMapper;
    private HttpClientConnector httpClientConnector;
    private LocationKeyDTO locationKeyDTO;
    private WeatherParamDTO weatherParamDTO;
    private String APIkey = "b4GBr5Mm1Y9Z8yeCXDIFNK2LhJSsH573";
    private String cityName = "274231";

    private boolean details = true;
    private boolean metrics = false;
    private String locationKey;
    private int timeRange = 15;
    private String language;

    public AccuWeatherClient() {
    }

    public AccuWeatherClient(ObjectMapper objectMapper, HttpClientConnector httpClientConnector, LocationKeyDTO locationKeyDTO, WeatherParamDTO weatherParamDTO) {
        this.objectMapper = objectMapper;
        this.httpClientConnector = httpClientConnector;
        this.locationKeyDTO = locationKeyDTO;
        this.weatherParamDTO = weatherParamDTO;
    }

    public void setLanguage(String language) {
        this.language = language;
    }



    public void getWeatherLocationKey(String language, String cityName) {
        String URL = String.format("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=%s&q=%s&language=%s&details=%s", APIkey,  cityName, language, details);
        String responseBody = httpClientConnector.initializeHttpConnection(URL);

        try {


            List<LocationKeyDTO> locationKeyDTO = Arrays.asList(objectMapper.readValue(responseBody, LocationKeyDTO[].class));
            String locationKey = locationKeyDTO.get(0).getKey();
            this.locationKey = locationKey;
            this.language = language;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot initialize connection to AccuWeather");

        }
    }





    public String getAccuWeatherForecast(String language, String cityName, LocalDateTime weatherDate) {
        String URL = String.format("http://dataservice.accuweather.com/forecasts/v1/daily/%sday/%s?apikey=%s&language=%s&details=%s&metric=%s", this.timeRange, locationKey, APIkey, language, details, metrics);
        String responseBody = httpClientConnector.initializeHttpConnection(URL);

        try {
            WeatherParamDTO weatherParamDTO = objectMapper.readValue(responseBody, WeatherParamDTO.class);
        //  String temperature = weatherParamDTO.getTemperature(); // to do
            return responseBody;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot initialize connection to AccuWeather");
            return null;
        }
    }
}
