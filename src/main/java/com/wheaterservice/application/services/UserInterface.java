package com.wheaterservice.application.services;

import com.wheaterservice.application.interfaces.inbound.WeatherController;
import com.wheaterservice.application.utils.Color;
import com.wheaterservice.application.utils.InputValidator;
import com.wheaterservice.domain.entities.Weather;

import java.util.List;
import java.util.Objects;


public class UserInterface {

    private InputValidator inputValidator;
    private WeatherController weatherController;

    private static final String CLOSED_APP_MESSAGE = "\nThank you for your time! \n" + "Good bye!";
    private static final String PROVIDE_LOCATION_NAME = "Please provide location name";
    private static final String PROVIDE_COUNTY_NAME = "Please provide country name";
    private static final String INVITATION_MESSAGE = "\n" +
        "==================================================================== \n" +
        "Welcome to the [ϟϟϟ WEATHER SERVICE ϟϟϟ], what would you like to do? \n" +
        "==================================================================== \n" + "\n" +
        Color.BLUE.get() + "1 => Obtain a weather forecast \n" + Color.RESET.get() +
        Color.BLUE.get() + "2 => Read existing weather entry from DB \n" + Color.RESET.get() +
        Color.BLUE.get() + "3 => Read all existing weather entries from DB \n" + Color.RESET.get() +
        Color.RED.get() + "4 => Close the application \n" + Color.RESET.get() +
        Color.RESET.get() + "↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ";
    private static final String WEATHER_LANGUAGE_MESSAGE = "\n" +
        "Please type wheather forecast message language \n" +
        "en => English \n" +
        "pl => Polish (polski) \n" +
        Color.RESET.get() + "↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ";
    private static final String WEATHER_DATE = "\n" +
        "Please type a specific date you would like to get forecast \n" +
        "Recommended date format: DD-MM-RRRR \n" +
        Color.RESET.get() + "↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ";

    public UserInterface(InputValidator inputValidator,  WeatherController weatherController) {
        this.inputValidator = inputValidator;
        this.weatherController = weatherController;
    }

    public void showInitialMenu() {
        while (true) {
            System.out.println(INVITATION_MESSAGE);
            int userInput = inputValidator.retrievesInteger();

            switch (userInput) {
                case 1:
                    obtainWeatherForecast();
                    break;
                case 2:
                    readWeatherEntry();
                    break;
                case 3:
                    readAllWeatherEntries();
                    break;
                case 4:
                    System.out.println(CLOSED_APP_MESSAGE);
                    return;
                default:
                    System.out.println("Invalid option, please choose a number from 1 to 3.");
            }
        }
    }


    private void readWeatherEntry() {
        System.out.println(WEATHER_DATE);
        String weatherDate = inputValidator.retrieveAndValidateDate();
        System.out.println(PROVIDE_LOCATION_NAME);
        String locationName = inputValidator.retrieveAndValidateLocation();
        List<Weather> readWeatherEntriy = weatherController.readWeatherEntry(locationName, weatherDate);

        if(readWeatherEntriy.isEmpty()) {
            System.out.println("No weather records found for the location: " + locationName + " and date: " + weatherDate + ".");
        } else {
            System.out.println("\nRetrieved weather entry:\n " + readWeatherEntriy);
        }
    }

    private void readAllWeatherEntries() {
        List<Weather> readWeatherEntriy = weatherController.readAllWeatherEntries();

        if(readWeatherEntriy.isEmpty()) {
            System.out.println("No weather records found in DB");
        } else {
            System.out.println("\nRetrieved weather entries:\n " + readWeatherEntriy);
        }
    }



    private void obtainWeatherForecast() {
        System.out.println(PROVIDE_LOCATION_NAME);
        String locationName = inputValidator.retrieveAndValidateLocation();
        System.out.println("Would you like to obtain current or historical weather record? Please type: C - for Current / H - for Historical");
        String weatherRecordTypeChoice = inputValidator.retrieveAndValidateLetter("[CH]{1}");
        String weatherDate = "01-01-2000";
        if("H".equalsIgnoreCase(weatherRecordTypeChoice)) {
            System.out.println(WEATHER_DATE);
            weatherDate = inputValidator.retrieveAndValidateDate();
        }

        Weather weatherForecast = weatherController.getWeatherForecast(weatherRecordTypeChoice, weatherDate, locationName);
        if(Objects.nonNull(weatherForecast)) {
            System.out.println(weatherForecast);
            System.out.println("\nWould you like to save the weather forecast? Type Y / N");
            String saveValue = inputValidator.retrieveAndValidateLetter("[YN]{1}");
            weatherController.saveWeatherForecast(weatherForecast, saveValue);
        }
    }

}
