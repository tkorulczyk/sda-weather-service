package com.wheaterservice.backend.weather;

import com.wheaterservice.backend.location.Location;
import com.wheaterservice.backend.weather.httpclients.AccuWeatherClient;
import com.wheaterservice.backend.weather.httpclients.OpenWeatherMapClient;
import com.wheaterservice.backend.weather.httpclients.WeatherStackClient;

import java.time.LocalDate;


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

    public String getWeatherStackForecast(String language, LocalDate weatherDate, String cityName) {
        weatherStackClient.getWeatherStackForecast(cityName);
        return weatherStackClient.getWeatherStackForecast(cityName);

    }


    public Weather saveWeatherMetrics(LocalDate date, Location location, int AWTemperature, int AWwindSpeed, int AWwindDir, int AWpressure, int AWhumidity, int OWMtemperature, int OWMwindSpeed, int OWMwindDir, int OWMpressure, int OWMhumidity, int WStemperature, int WSwindSpeed, int WSwindDir, int WSpressure, int WShumidity, int AVGtemperature, int AVGWindSpeed, int AVGWindDir, int AVGpressure, int AVGhumidity) {


        Weather weather = new Weather(date, location, AWTemperature, AWwindSpeed, AWwindDir, AWpressure, AWhumidity, OWMtemperature, OWMwindSpeed, OWMwindDir, OWMpressure, OWMhumidity,  WStemperature, WSwindSpeed, WSwindDir, WSpressure, WShumidity, AVGtemperature, AVGWindSpeed, AVGWindDir, AVGpressure, AVGhumidity);
        return weatherRepository.saveWeatherMetrics(weather);
    }


    private int calculateAverageWindSpeed() {
        int averageWindSpeed =
                openWeatherMapClient.getWindSpeed() +
                        weatherStackClient.getWindSpeed() +
                        accuWeatherClient.getWindSpeed() / 3;
        return averageWindSpeed;
    }

    private int calculateAverageWindDir() {
        int averageWindDir =
                openWeatherMapClient.getWindDir() +
                        weatherStackClient.getWindDir() +
                        accuWeatherClient.getWindDir() / 3;
        return averageWindDir;
    }

    private int calculateAverageHumidity() {
        int averageHumidity =
                openWeatherMapClient.getHumidity() +
                        weatherStackClient.getHumidity() +
                        accuWeatherClient.getHumidity() / 3;
        return averageHumidity;
    }

    private int calculateAveragePressure() {
        int averagePressure =
                openWeatherMapClient.getPressure() +
                        weatherStackClient.getPressure() +
                        accuWeatherClient.getPressure() / 3;
        return averagePressure;
    }

    private int calculateAverageTemperature() {
        int averageTemperature =
                openWeatherMapClient.getTemperature() +
                        weatherStackClient.getTemperature() +
                        accuWeatherClient.getTemperature() / 3;
        return averageTemperature;
    }
}
