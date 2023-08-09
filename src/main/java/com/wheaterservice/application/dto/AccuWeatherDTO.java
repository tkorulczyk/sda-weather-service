package com.wheaterservice.application.dto;

/**
 * Created on 09.08.2023
 *
 * @author Tomasz Korulczyk
 */


import lombok.Data;

@Data
public class AccuWeatherDTO {
  private com.wheaterservice.application.dto.AccuWeatherDTO.DailyForecasts[] dailyForecast;

  @Data
  public static class DailyForecasts {

  }

}

