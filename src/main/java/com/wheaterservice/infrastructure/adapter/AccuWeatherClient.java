package com.wheaterservice.infrastructure.adapter;

/**
 * Created on 09.08.2023
 *
 * @author Tomasz Korulczyk
 */


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wheaterservice.application.dto.AccuWeatherDTO;
import com.wheaterservice.application.dto.LocationKeyDTO;
import com.wheaterservice.application.interfaces.outbound.HttpClientConnector;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class AccuWeatherClient {
  private ObjectMapper objectMapper;
  private HttpClientConnector httpClientConnector;
  private final String APIkey = "b4GBr5Mm1Y9Z8yeCXDIFNK2LhJSsH573";
  private boolean details = true;
  private boolean metrics = false;
  private String locationKey;
  private int timeRange = 5;




  public AccuWeatherClient(ObjectMapper objectMapper, HttpClientConnector httpClientConnector, LocationKeyDTO locationKeyDTO, AccuWeatherDTO accuWeatherDTO) {
    this.objectMapper = objectMapper;
    this.httpClientConnector = httpClientConnector;
  }

  public void getWeatherLocationKey(String language, String cityName) {
    String URL = String.format("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=%s&q=%s&language=%s&details=%s", APIkey, cityName, language, details);
    String responseBody = httpClientConnector.initializeHttpConnection(URL);

    try {


      List<LocationKeyDTO> locationKeyDTO = Arrays.asList(objectMapper.readValue(responseBody, LocationKeyDTO[].class));
      this.locationKey = locationKeyDTO.get(0).getKey();

    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Cannot initialize connection to AccuWeather");

    }
  }


  public String getAccuWeatherForecast(String language, String cityName, LocalDate weatherDate) {
    String URL = String.format("http://dataservice.accuweather.com/forecasts/v1/daily/%sday/%s?apikey=%s&language=%s&details=%s&metric=%s", this.timeRange, locationKey, APIkey, language, details, metrics);
    String responseBody = httpClientConnector.initializeHttpConnection(URL);

    try {
      List<AccuWeatherDTO.DailyForecasts> dailyForecasts = Arrays.asList(objectMapper.readValue(responseBody, AccuWeatherDTO.DailyForecasts[].class));
      AccuWeatherDTO accuWeatherDTO = objectMapper.readValue(responseBody, AccuWeatherDTO.class);
      String date = dailyForecasts.get(0).toString();
      System.out.println(date);


      //  String temperature = weatherParamDTO.getTemperature(); // to do

//            String forecastMessage = String.format("\nCurrent weather metrics for %s are \n" +
//                            "↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ " +
//                            "\n temperature: %s,\n pressure: %s,\n humidity: %s,\n wind speed: %s,\n wind direction: %s %s,\n weather description: %s",
//                    cityName.toUpperCase(), temperature, pressure, humidity, WindSpeed, WindDir, arrow, weatherDescription);


      return responseBody;

    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Cannot initialize connection to AccuWeather");
      return null;
    }
  }
}
