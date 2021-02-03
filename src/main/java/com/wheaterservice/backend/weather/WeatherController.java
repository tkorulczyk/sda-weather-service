package com.wheaterservice.backend.weather;

import java.time.LocalDate;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class WeatherController {
private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public String getWeatherForecast(String language, String weatherDate, String cityName) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(weatherDate,dateTimeFormatter);

      //  return weatherService.getOpenWeatherMapForecast(cityName);
      //  return weatherService.getAccuWeatherForecast(language, date, cityName);
        return weatherService.getWeatherStackForecast(language, date, cityName);
    }
}
