package com.wheaterservice.frontend;

import com.wheaterservice.backend.location.LocationController;
import com.wheaterservice.backend.weather.WeatherController;


public class UserInterface {


    private InputValidator inputValidator;
    private final LocationController locationController;
    private WeatherController weatherController;


    private final String CLOSED_APP_MESSAGE = "\nThank you for your time! \n" + "Good bye!";
    private final String PROVIDE_LOCATION_NAME = "Please provide location name";
    private final String PROVIDE_REGION_NAME = "Please provide region name";
    private final String PROVIDE_COUNTY_NAME = "Please provide country name";
    private final String PROVIDE_LATITUDE = "Please provide latitude of the location";
    private final String PROVIDE_LONGITUDE = "Please provide longitude of the location";
    private final String INVITATION_MESSAGE = "\n" +
            "==================================================================== \n" +
            "Welcome to the [ϟϟϟ WEATHER SERVICE ϟϟϟ], what would you like to do? \n" +
            "==================================================================== \n" + "\n" +
            Color.BLUE + "1 => Add new location \n" + Color.RESET +
            Color.BLUE + "2 => Read existing weather entries \n" + Color.RESET +
            Color.BLUE + "3 => Obtain a weather forecast \n" + Color.RESET +
            Color.RED + "4 => Close the application \n" + Color.RESET +
            Color.RESET + "↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ";

    private final String WEATHER_LANGUAGE_MESSAGE = "\n" +
            "Please type wheather forecast message language \n" +
            "en => English \n" +
            "pl => Polish (polski) \n" +
            Color.RESET + "↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ";

    private final String WEATHER_DATE = "\n" +
            "Please type a specific date you would like to get forecast [optiona] \n" +
            "Recommended date format: DD-MM-RRRR \n" +
            Color.RESET + "↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ";


    public UserInterface(InputValidator inputValidator, LocationController locationController, WeatherController weatherController) {
        this.inputValidator = inputValidator;
        this.locationController = locationController;
        this.weatherController = weatherController;
    }

    void showInitialMenu() {
        while (true) {
            System.out.println(INVITATION_MESSAGE);
            int userInput = inputValidator.retrievesInteger();

            switch (userInput) {
                case 1:
                    addNewLocation();
                    break;
                case 2:
                    readWeatherEntries();
                    break;
                case 3:
                    obtainWeatherForecast();
                    break;
                case 4:
                    System.out.println(CLOSED_APP_MESSAGE);
                    return;
            }
        }
    }

    private void addNewLocation() {
        System.out.println(PROVIDE_COUNTY_NAME);
        String countryName = inputValidator.retrieveAndValidateLocation();
        System.out.println(PROVIDE_REGION_NAME);
        String regionName = inputValidator.retrieveAndValidateLocation();
        System.out.println(PROVIDE_LOCATION_NAME);
        String locationName = inputValidator.retrieveAndValidateLocation();
        System.out.println(PROVIDE_LATITUDE);
        String latitude = inputValidator.retrieveAndValidateCoordinates();
        System.out.println(PROVIDE_LONGITUDE);
        String longitude = inputValidator.retrieveAndValidateCoordinates();
        System.out.println();
        String weatherEntry = locationController.createNewLocation(countryName, regionName, locationName, latitude, longitude);
    }

    private void readWeatherEntries() {
        String readWeatherLocation = locationController.readLocations();
        System.out.println();
        System.out.println("Retreived Locations:\n " + readWeatherLocation);
    }

    private void obtainWeatherForecast() {
        System.out.println(WEATHER_LANGUAGE_MESSAGE);
        String language = inputValidator.retrieveAndValidateLanguage();
        System.out.println(WEATHER_DATE);
        String weatherDate = inputValidator.retrieveAndValidateDate();
        System.out.println(PROVIDE_LOCATION_NAME);
        String locationName = inputValidator.retrieveAndValidateLocation();
        String obtainWeatherLocation = weatherController.getWeatherForecast(language, weatherDate, locationName);
        System.out.println(obtainWeatherLocation);
    }
}

