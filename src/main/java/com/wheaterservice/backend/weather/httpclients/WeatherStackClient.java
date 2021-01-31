package com.wheaterservice.backend.weather.httpclients;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class WeatherStackClient {
    private ObjectMapper objectMapper;
    private HttpClientConnector httpClientConnector;
    private String APIkey = "9d6b07b8fdc422737f0ca98fa0da4009";
    private int timeRange = 14;
    private String language;

    public WeatherStackClient(ObjectMapper objectMapper, HttpClientConnector httpClientConnector) {
        this.objectMapper = objectMapper;
        this.httpClientConnector = httpClientConnector;
    }

    public String getWeatherStackForecast(String cityName) {
        String URL = String.format("http://api.weatherstack.com/current?access_key=%s&query=%s", APIkey, cityName);

        String responseBody = httpClientConnector.initializeHttpConnection(URL);

        try {
            WeatherStackDTO weatherStackDTO = objectMapper.readValue(responseBody, WeatherStackDTO.class);
            int temperature = weatherStackDTO.getCurrent().getTemperature();
            int wind_speed = weatherStackDTO.getCurrent().getWind_speed();
            String wind_dir = weatherStackDTO.getCurrent().getWind_dir();
            int pressure = weatherStackDTO.getCurrent().getPressure();
            int humidity = weatherStackDTO.getCurrent().getHumidity();

            String arrow = null;
            if (wind_dir.equals("N")) {arrow = "↑";}
            if (wind_dir.equals("E")) {arrow = "→";}
            if (wind_dir.equals("S")) {arrow = "↓";}
            if (wind_dir.equals("W")) {arrow = "←";}
            if (wind_dir.contains("NE")) {arrow = "↗";}
            if (wind_dir.contains("SE")) {arrow = "↘";}
            if (wind_dir.contains("SW")) {arrow = "↙";}
            if (wind_dir.contains("NW")) {arrow = "↖";}

            String forecastMessage = String.format("\nCurrent weather metrics for %s are \n" +
                            "↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ " +
                            "\n temperature: %s,\n pressure: %s,\n humidity: %s,\n wind speed: %s,\n wind direction: %s %s",
                    cityName.toUpperCase(), temperature, pressure, humidity, wind_speed, wind_dir, arrow);

            return forecastMessage;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot initialize connection to AccuWeather");
            return null;
        }
    }
}
