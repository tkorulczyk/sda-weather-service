package com.wheaterservice.backend.httpclients;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.text.html.parser.Entity;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AccuWeatherClient {
    private final ObjectMapper objectMapper;

    public AccuWeatherClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getWeatherForecast() {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://dataservice.accuweather.com/forecasts/v1/daily/1day/{locationKey}"))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();

            LocationDTO locationDTO = objectMapper.readValue(responseBody, LocationDTO.class);
            // to do
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot retreive information from AccuWeather");
            return null;
        }
    }
}
