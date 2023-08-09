package com.wheaterservice.application.services;

import com.wheaterservice.application.interfaces.outbound.WeatherRepository;

import com.wheaterservice.domain.entities.Weather;

import com.wheaterservice.infrastructure.adapter.AccuWeatherClient;
import com.wheaterservice.infrastructure.adapter.OpenWeatherMapClient;
import com.wheaterservice.infrastructure.adapter.WeatherStackClient;

import java.time.LocalDate;
import java.util.List;


public class WeatherService {
    private AccuWeatherClient accuWeatherClient;
    private WeatherStackClient weatherStackClient;
    private OpenWeatherMapClient openWeatherMapClient;
    private WeatherRepository weatherRepository;

    public WeatherService(AccuWeatherClient accuWeatherClient, WeatherStackClient weatherStackClient, OpenWeatherMapClient openWeatherMapClient, WeatherRepository weatherRepository) {
        this.accuWeatherClient = accuWeatherClient;
        this.weatherStackClient = weatherStackClient;
        this.openWeatherMapClient = openWeatherMapClient;
        this.weatherRepository = weatherRepository;
    }

    public String getOpenWeatherMapForecast(String cityName) {
        return openWeatherMapClient.getOpenWeatherMapForecast(cityName);
    }

    public String getAccuWeatherForecast(String language, LocalDate weatherDate, String cityName) {
        accuWeatherClient.getWeatherLocationKey(language, cityName);
        return accuWeatherClient.getAccuWeatherForecast(language, cityName, weatherDate);
    }

    public Weather getWeatherStackForecast(String weatherRecordTypeChoice, String weatherDate, String cityName) {
        return weatherStackClient.getWeatherStackForecast(weatherRecordTypeChoice, weatherDate, cityName);
    }

    public Weather saveWeatherMetrics(Weather weather) {
        return weatherRepository.saveWeatherMetrics(weather);
    }

    public List<Weather> readWeatherEntry(String location, String date) {
        return weatherRepository.readEntryByLocationAndDate(location, date);
    }

    public List<Weather> readAllWeatherEntries() {
        return weatherRepository.readAllEntries();
    }

}
