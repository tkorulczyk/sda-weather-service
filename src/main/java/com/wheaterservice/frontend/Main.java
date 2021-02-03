package com.wheaterservice.frontend;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wheaterservice.backend.weather.WeatherRepository;
import com.wheaterservice.backend.weather.httpclients.*;
import com.wheaterservice.backend.location.DatabaseInputValidator;
import com.wheaterservice.backend.location.LocationController;
import com.wheaterservice.backend.location.LocationRepositoryImpl;
import com.wheaterservice.backend.location.LocationService;
import com.wheaterservice.backend.weather.WeatherController;
import com.wheaterservice.backend.weather.WeatherService;

public class Main {
    private static UserInterface userInterface;

    static {
        InputValidator inputValidator = new InputValidator();
        LocationRepositoryImpl locationRepository = new LocationRepositoryImpl();
        DatabaseInputValidator dbValidator = new DatabaseInputValidator();
        LocationService locationService = new LocationService(locationRepository, dbValidator);
        HttpClientConnector httpClientConnector = new HttpClientConnectorImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        LocationKeyDTO locationKeyDTO = new LocationKeyDTO();
        AccuWeatherDTO accuWeatherDTO = new AccuWeatherDTO();
        OpenWeatherMapDTO openWeatherMapDTO = new OpenWeatherMapDTO();
        WeatherRepository weatherRepository = new WeatherRepository();
        OpenWeatherMapClient openWeatherMapClient = new OpenWeatherMapClient(objectMapper, httpClientConnector, openWeatherMapDTO);
        WeatherStackClient weatherStackClient = new WeatherStackClient(objectMapper, httpClientConnector);
        AccuWeatherClient accuWeatherClient = new AccuWeatherClient(objectMapper, httpClientConnector,locationKeyDTO, accuWeatherDTO);
        WeatherService weatherService = new WeatherService(accuWeatherClient, weatherStackClient, openWeatherMapClient,weatherRepository);
        LocationController locationController = new LocationController(locationService, weatherService);
        WeatherController weatherController = new WeatherController(weatherService);
        UserInterface userInterface = new UserInterface(inputValidator, locationController, weatherController);
        userInterface.showInitialMenu();
    }


    public static void main(String[] args) {
     //   userInterface.showInitialMenu();
    }
}
