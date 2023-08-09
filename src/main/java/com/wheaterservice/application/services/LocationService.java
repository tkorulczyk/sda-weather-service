package com.wheaterservice.application.services;

import com.wheaterservice.application.interfaces.outbound.WeatherRepository;
import com.wheaterservice.application.utils.DatabaseInputValidator;
import com.wheaterservice.domain.entities.Weather;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class LocationService {

  private WeatherRepository weatherRepository;


  public List<Weather> readLocations(String location, String date) {
    return weatherRepository.readEntryByLocationAndDate(location, date);
  }

  public String convertToTitleCaseIteratingChars(String text) {
    StringBuilder converted = new StringBuilder();
    boolean convertNext = true;
    for (char ch : text.toCharArray()) {
      if (Character.isSpaceChar(ch)) {
        convertNext = true;
      } else if (convertNext) {
        ch = Character.toTitleCase(ch);
        convertNext = false;
      } else {
        ch = Character.toLowerCase(ch);
      }
      converted.append(ch);
    }
    return converted.toString();
  }
}
