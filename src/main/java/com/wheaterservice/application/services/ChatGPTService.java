package com.wheaterservice.application.services;

import com.wheaterservice.infrastructure.adapter.ChatGPTClient;
import lombok.RequiredArgsConstructor;

/**
 * Created on 10.08.2023
 *
 * @author Tomasz Korulczyk
 */


@RequiredArgsConstructor
public class ChatGPTService {
  private final ChatGPTClient chatGPTClient;

  public String getWeatherRecommendation(String weatherData) {
    String primer = "You are a weather and planning assistant. Analyze the provided weather data and recommend appropriate clothing and potential outdoor activities. Here's the weather report: ";
    return chatGPTClient.fetchResponse(primer + weatherData);
  }
}
