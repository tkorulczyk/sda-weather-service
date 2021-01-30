package com.wheaterservice.backend;

import com.wheaterservice.backend.httpclients.AccuWeatherClient;

public class WeatherService {
    private AccuWeatherClient accuWeatherClient;

    public WeatherService(AccuWeatherClient accuWeatherClient) {
        this.accuWeatherClient = accuWeatherClient;
    }

    public void setWeatherMessageLanguage(String language) {
        accuWeatherClient.setLanguage(language);
    }

    public String getWeatherForecast() {
        return accuWeatherClient.getWeatherForecast();
    }

}
