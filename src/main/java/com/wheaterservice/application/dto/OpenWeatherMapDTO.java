package com.wheaterservice.application.dto;

/**
 * Created on 09.08.2023
 *
 * @author Tomasz Korulczyk
 */


import lombok.Data;

@Data
public class OpenWeatherMapDTO {
  private Main main;
  private Wind wind;

  @Data
  public static class Main {
    private int temp;
    private int pressure;
    private int humidity;
  }

  @Data
  public static class Wind {
    private int speed;
    private int deg;
  }


}
