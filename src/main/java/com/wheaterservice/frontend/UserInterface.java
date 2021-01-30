package com.wheaterservice.frontend;

import com.wheaterservice.backend.LocationController;
import com.wheaterservice.backend.WeatherController;


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
            "========================================================== \n" +
            "Welcome to the ⌂ weather service ⌂, what would you like to do? \n" +
            "========================================================== \n" + "\n" +
            Color.BLUE + "1 => Add new location \n" + Color.RESET +
            Color.BLUE + "2 => Read existing weather entries \n" + Color.RESET +
            Color.BLUE + "3 => Obtain a weather forecast \n" + Color.RESET +
            Color.RED + "4 => Close the application \n" + Color.RESET +
            Color.RESET + "↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ";

    private final String WEATHER_LANGUAGE_MESSAGE = "\n" +
            "========================================================== \n" +
            "In which language would you like to display the weather forecast message? \n" +
            "========================================================== \n" + "\n" +
           "1 => English \n" +
            "2 => Polish (język polski) \n" +
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
        showWeatherForecastMenu();
        String obtainWeatherLocation = weatherController.getWeatherForecast();
        System.out.println();
        System.out.println(obtainWeatherLocation);
    }


    private void showWeatherForecastMenu() {
        System.out.println(WEATHER_LANGUAGE_MESSAGE);
        int userInput2 = inputValidator.retrievesInteger();

        switch (userInput2) {
            case 1:
                weatherController.setWeatherMessageLanguage("en-us");
                break;
            case 2:
                weatherController.setWeatherMessageLanguage("pl");
                break;
        }
    }

}


//            System.out.println(PROVIDE_LOCATION_NAME);
//           // String locationName = inputValidator.retrievesString();
//            System.out.println("Provide language");
//            String latitude = inputValidator.retrievesString();
//            System.out.println("Provide weather time range unit");
//            String longitude = inputValidator.retrievesString();
//            System.out.println();
//         String weatherEntry = locationController.createNewLocation(locationName, latitude,longitude);


