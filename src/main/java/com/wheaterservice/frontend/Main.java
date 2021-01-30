package com.wheaterservice.frontend;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wheaterservice.backend.*;
import com.wheaterservice.backend.httpclients.AccuWeatherClient;
import com.wheaterservice.backend.httpclients.HttpClientConnector;
import com.wheaterservice.backend.httpclients.HttpClientConnectorImpl;

public class Main {

    public static void main(String[] args) {


        InputValidator inputValidator = new InputValidator();
        LocationRepositoryImpl locationRepository = new LocationRepositoryImpl();
        DatabaseInputValidator dbValidator = new DatabaseInputValidator();
        LocationService locationService = new LocationService(locationRepository, dbValidator);
        HttpClientConnector httpClientConnector = new HttpClientConnectorImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        AccuWeatherClient accuWeatherClient = new AccuWeatherClient(objectMapper, locationService, httpClientConnector);
        WeatherService weatherService = new WeatherService(accuWeatherClient);
        LocationController locationController = new LocationController(locationService, weatherService);
        WeatherController weatherController = new WeatherController(weatherService);
        UserInterface userInterface = new UserInterface(inputValidator, locationController, weatherController);

        userInterface.showInitialMenu();
    }
}
