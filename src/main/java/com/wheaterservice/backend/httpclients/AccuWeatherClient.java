package com.wheaterservice.backend.httpclients;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wheaterservice.backend.LocationService;


import javax.xml.bind.SchemaOutputResolver;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AccuWeatherClient {
    private ObjectMapper objectMapper;
    private LocationService locationService;
    String APIkey = "b4GBr5Mm1Y9Z8yeCXDIFNK2LhJSsH573";
    private String timeRange = "daily/1day";
    private String cityName = "274231";
    private String language = "pl";
    private boolean details = true;
    private boolean metrics = false;
    private String URL = String.format("http://dataservice.accuweather.com/forecasts/v1/%s/%s?apikey=%s&language=%s&details=%s&metric=%s",timeRange,cityName,APIkey,language,details,metrics);


    public AccuWeatherClient() {
    }

    public AccuWeatherClient(LocationService locationService) {
        this.locationService = locationService;
    }

    public AccuWeatherClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getWeatherForecast() {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();


            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            WheaterParamDTO wheaterParamDTO = objectMapper.readValue(responseBody,WheaterParamDTO.class);
            String temperature = wheaterParamDTO.getTemperature();
            System.out.println(responseBody);

            return temperature;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot retrieve information from AccuWeather");
            return null;
        }
    }
}
