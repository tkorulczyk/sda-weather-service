package com.wheaterservice.backend.weather.httpclients;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class WeatherStackClient {
    private ObjectMapper objectMapper;
    private HttpClientConnector httpClientConnector;
    private String APIkey = "9d6b07b8fdc422737f0ca98fa0da4009";
    private String language;
    private int temperature;
    private int WindSpeed;
    private int WindDir;
    private int pressure;
    private int humidity;

    public int getTemperature() {
        return temperature;
    }

    public int getWindSpeed() {
        return WindSpeed;
    }

    public int getWindDir() {
        return WindDir;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }


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
            int WindSpeed = weatherStackDTO.getCurrent().getWind_speed();
            int WindDir = weatherStackDTO.getCurrent().getDegrees();
            int pressure = weatherStackDTO.getCurrent().getPressure();
            int humidity = weatherStackDTO.getCurrent().getHumidity();
            String weatherDescription = weatherStackDTO.getCurrent().getWeather_descriptions()
                    .toString().replace("]","").replace("[","");

            this.temperature = temperature;
            this.WindSpeed = WindSpeed;
            this.WindDir = WindDir;
            this.pressure = pressure;
            this.humidity = humidity;


            String arrow = null;
            if (WindDir >= 337.5 && WindDir <= 22.5) {
                arrow = "↑";
            }
            if (WindDir >= 67.5 && WindDir <= 112.5) {
                arrow = "→";
            }
            if (WindDir >= 157.5 && WindDir <= 202.5) {
                arrow = "↓";
            }
            if (WindDir >= 247.5 && WindDir <= 292.5) {
                arrow = "←";
            }
            if (WindDir > 22.5 && WindDir < 67.5) {
                arrow = "↗";
            }
            if (WindDir > 112.5 && WindDir < 157.5) {
                arrow = "↘";
            }
            if (WindDir > 202.5 && WindDir < 247.5) {
                arrow = "↙";
            }
            if (WindDir > 292.5 && WindDir < 337.5) {
                arrow = "↖";
            }

            String forecastMessage = String.format("\nCurrent weather metrics for %s are \n" +
                            "↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ " +
                            "\n temperature: %s,\n pressure: %s,\n humidity: %s,\n wind speed: %s,\n wind direction: %s %s,\n weather description: %s",
                    cityName.toUpperCase(), temperature, pressure, humidity, WindSpeed, WindDir, arrow, weatherDescription);

            return forecastMessage;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot initialize connection to AccuWeather");
            return null;
        }
    }
}
