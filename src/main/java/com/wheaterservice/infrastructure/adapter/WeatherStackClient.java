package com.wheaterservice.infrastructure.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wheaterservice.application.dto.WeatherStackDTO;
import com.wheaterservice.application.interfaces.outbound.HttpClientConnector;
import com.wheaterservice.domain.entities.Weather;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.wheaterservice.application.utils.Utils.reverseDateFormat;

@Getter
public class WeatherStackClient {
  private ObjectMapper objectMapper;
  private HttpClientConnector httpClientConnector;
  private String APIkey = "9d6b07b8fdc422737f0ca98fa0da4009";


  public WeatherStackClient(ObjectMapper objectMapper, HttpClientConnector httpClientConnector) {
    this.objectMapper = objectMapper;
    this.httpClientConnector = httpClientConnector;
  }

  public Weather getWeatherStackForecast(String weatherRecordTypeChoice, String weatherDate, String cityName) {
    String url = buildWeatherUrl(cityName, reverseDateFormat(weatherDate), weatherRecordTypeChoice);
    String responseBody = httpClientConnector.initializeHttpConnection(url);

    if(responseBody.contains("\"success\":false")) {
      System.out.println(String.format("City with such name %s was not found",cityName));
      System.out.println("Response from API: " + responseBody);
      return null;
    }

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate parsedDate = LocalDate.parse(weatherDate, dateTimeFormatter);

    // Deserialize response body to WeatherStackDTO
    WeatherStackDTO weatherStackDTO = null;
    try {
      weatherStackDTO = objectMapper.readValue(responseBody, WeatherStackDTO.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    // Get Current object from WeatherStackDTO
    final WeatherStackDTO.Current w = weatherStackDTO.getCurrent();

    Weather weather = Weather.builder()
        .date(parsedDate)
        .location(cityName)
        .temperature(w.getTemperature())
        .airPressure(w.getPressure())
        .cloudCover(w.getCloudCover())
        .feelsLike(w.getFeelsLike())
        .humidity(w.getHumidity())
        .uvIndex(w.getUvIndex())
        .weatherDescription(w.getWeatherDescription().get(0)) // Assuming you want the first description
        .weatherServiceType("Weather Stack")
        .windDir(w.getWindDir())
        .windSpeed(w.getWindSpeed())
        .visibility(w.getVisibility())
        .build();

    return weather;
  }

  private String buildWeatherUrl(String cityName, String weatherDate, String weatherRecordTypeChoice) {
    String baseUrl = "http://api.weatherstack.com/%s?access_key=%s&query=%s";
    final boolean isWeatherRecordTypeHistorical = "H".equalsIgnoreCase(weatherRecordTypeChoice);
    String type = isWeatherRecordTypeHistorical ? "historical" : "current";
    String url = String.format(baseUrl, type, APIkey, cityName);

    if (isWeatherRecordTypeHistorical) {
      url = url + String.format("&historical_date=%s", weatherDate);
    }

    return url;
  }

}
