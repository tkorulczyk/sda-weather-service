package com.wheaterservice.backend;

public class WeatherController {
private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public void setWeatherMessageLanguage(String language) {
         weatherService.setWeatherMessageLanguage(language);
    }

    public String getWeatherForecast() {
        return weatherService.getWeatherForecast();
    }
}
