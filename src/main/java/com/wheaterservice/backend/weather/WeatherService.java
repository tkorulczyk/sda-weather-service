package com.wheaterservice.backend.weather;

import com.wheaterservice.backend.weather.httpclients.AccuWeatherClient;
import com.wheaterservice.backend.weather.httpclients.WeatherStackClient;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeatherService {
    private AccuWeatherClient accuWeatherClient;
    private WeatherStackClient weatherStackClient;


    public WeatherService(AccuWeatherClient accuWeatherClient, WeatherStackClient weatherStackClient) {
        this.accuWeatherClient = accuWeatherClient;
        this.weatherStackClient = weatherStackClient;
    }

    public String getAccuWeatherForecast(String language, LocalDateTime weatherDate, String cityName) {
        accuWeatherClient.getWeatherLocationKey(language, cityName);
       return accuWeatherClient.getAccuWeatherForecast(language, cityName, weatherDate);

    }

    public String getWeatherStackForecast(String language, LocalDate weatherDate, String cityName) {
        weatherStackClient.getWeatherStackForecast(cityName);
        return weatherStackClient.getWeatherStackForecast(cityName);

    }


}
