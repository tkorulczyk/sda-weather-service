package com.wheaterservice.exceptions;

/**
 * Created on 26.07.2023
 *
 * @author Tomasz Korulczyk
 */

public class WeatherServiceException extends RuntimeException {
  public WeatherServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
