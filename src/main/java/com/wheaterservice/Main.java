package com.wheaterservice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wheaterservice.application.dto.AccuWeatherDTO;
import com.wheaterservice.application.dto.LocationKeyDTO;
import com.wheaterservice.application.dto.OpenWeatherMapDTO;
import com.wheaterservice.application.interfaces.inbound.WeatherController;
import com.wheaterservice.application.interfaces.outbound.HttpClientConnector;
import com.wheaterservice.application.interfaces.outbound.WeatherRepository;
import com.wheaterservice.application.services.ChatGPTService;
import com.wheaterservice.application.services.UserInterface;
import com.wheaterservice.application.services.WeatherService;
import com.wheaterservice.application.utils.InputValidator;
import com.wheaterservice.infrastructure.adapter.AccuWeatherClient;
import com.wheaterservice.infrastructure.adapter.ChatGPTClient;
import com.wheaterservice.infrastructure.adapter.ChatGPTClientImpl;
import com.wheaterservice.infrastructure.adapter.HttpClientConnectorImpl;
import com.wheaterservice.infrastructure.adapter.OpenWeatherMapClient;
import com.wheaterservice.infrastructure.adapter.WeatherStackClient;

public class Main {

  public static void main(String[] args) {
    InputValidator inputValidator = new InputValidator();
    HttpClientConnector httpClientConnector = new HttpClientConnectorImpl();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    LocationKeyDTO locationKeyDTO = new LocationKeyDTO();
    AccuWeatherDTO accuWeatherDTO = new AccuWeatherDTO();
    OpenWeatherMapDTO openWeatherMapDTO = new OpenWeatherMapDTO();
    WeatherRepository weatherRepository = new WeatherRepository();
    OpenWeatherMapClient openWeatherMapClient = new OpenWeatherMapClient(objectMapper, httpClientConnector, openWeatherMapDTO);
    WeatherStackClient weatherStackClient = new WeatherStackClient(objectMapper, httpClientConnector);
    AccuWeatherClient accuWeatherClient = new AccuWeatherClient(objectMapper, httpClientConnector, locationKeyDTO, accuWeatherDTO);
    WeatherService weatherService = new WeatherService(accuWeatherClient, weatherStackClient, openWeatherMapClient, weatherRepository);
    WeatherController weatherController = new WeatherController(weatherService);
    ChatGPTClient chatGPTClient = new ChatGPTClientImpl(httpClientConnector, objectMapper);
    ChatGPTService chatGPTService = new ChatGPTService(chatGPTClient);
    UserInterface userInterface = new UserInterface(inputValidator, weatherController, chatGPTService);
    userInterface.showInitialMenu();
  }
}
