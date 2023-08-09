package com.wheaterservice.application.interfaces.inbound;

import com.wheaterservice.application.services.WeatherService;
import com.wheaterservice.domain.entities.Weather;

import java.util.List;
import java.util.Objects;

public class WeatherController {
  private WeatherService weatherService;

  public WeatherController(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  public Weather getWeatherForecast(String weatherRecordTypeChoice, String weatherDate, String cityName) {
    return weatherService.getWeatherStackForecast(weatherRecordTypeChoice, weatherDate, cityName);
  }

  public void saveWeatherForecast(Weather weather, String saveValue) {

    if ("Y".equalsIgnoreCase(saveValue) && Objects.nonNull(weather)) {
      weatherService.saveWeatherMetrics(weather);
    } else {
      System.out.println("Weather record has not been saved!");
    }
  }

  public List<Weather> readWeatherEntry(String location, String date) {
    return weatherService.readWeatherEntry(location, date);
  }

  public List<Weather> readAllWeatherEntries() {
    return weatherService.readAllWeatherEntries();
  }
}
