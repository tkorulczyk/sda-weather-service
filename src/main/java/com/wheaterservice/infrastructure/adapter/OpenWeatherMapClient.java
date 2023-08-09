package com.wheaterservice.infrastructure.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wheaterservice.application.dto.OpenWeatherMapDTO;
import com.wheaterservice.application.interfaces.outbound.HttpClientConnector;

import java.io.IOException;

public class OpenWeatherMapClient {
    private ObjectMapper objectMapper;
    private HttpClientConnector httpClientConnector;
    private String APIkey = "5b87947371d54e66566fe002328e146b";
    private int temperature;
    private int WindSpeed;
    private int WindDir;
    private int pressure;
    private int humidity;


    public OpenWeatherMapClient(ObjectMapper objectMapper, HttpClientConnector httpClientConnector, OpenWeatherMapDTO openWeatherMapDTO) {
        this.objectMapper = objectMapper;
        this.httpClientConnector = httpClientConnector;
    }

    public String getOpenWeatherMapForecast(String cityName) {
        String URL = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", cityName, APIkey);
        String responseBody = httpClientConnector.initializeHttpConnection(URL);

        try {
            OpenWeatherMapDTO openWeatherMapDTO = objectMapper.readValue(responseBody, OpenWeatherMapDTO.class);
            int temperature = openWeatherMapDTO.getMain().getTemp();
            int WindSpeed = openWeatherMapDTO.getWind().getSpeed();
            int WindDir = openWeatherMapDTO.getWind().getDeg();
            int pressure = openWeatherMapDTO.getMain().getPressure();
            int humidity = openWeatherMapDTO.getMain().getHumidity();
            int temperatureInCelsius = (int) Math.round(convertKelvinToCelsius(temperature));

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
                            "\n temperature: %s,\n pressure: %s,\n humidity: %s,\n wind speed: %s,\n wind direction: %s %s",
                    cityName.toUpperCase(), temperatureInCelsius, pressure, humidity, WindSpeed, WindDir, arrow);

            return forecastMessage;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot initialize connection to AccuWeather");
            return null;
        }
    }

    private double convertKelvinToCelsius(double temperature) {
        temperature = temperature - 273.15;
        return temperature;
    }


    private String URL2 = "api.openweathermap.org/data/2.5/weather?q=Lublin&appid=5b87947371d54e66566fe002328e146b";

}
