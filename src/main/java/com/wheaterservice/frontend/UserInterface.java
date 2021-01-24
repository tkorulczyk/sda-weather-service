package com.wheaterservice.frontend;

import com.wheaterservice.backend.LocationController;

public class UserInterface {


    private InputValidator inputValidator = new InputValidator();
    private final LocationController locationController;

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
            Colors.BLUE + "1 => Add new location \n" + Colors.RESET +
            Colors.BLUE + "2 => Read existing weather entries \n" + Colors.RESET +
            Colors.BLUE + "3 => Obtain a weather forecast \n" + Colors.RESET +
            Colors.RED + "4 => Close the application \n" + Colors.RESET +
            Colors.RESET + "↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ";

    public UserInterface(LocationController locationController) {
        this.locationController = locationController;
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
        String countryName = inputValidator.retrievesString();
        System.out.println(PROVIDE_REGION_NAME);
        String regionName = inputValidator.retrievesString();
        System.out.println(PROVIDE_LOCATION_NAME);
        String locationName = inputValidator.retrievesString();
        System.out.println(PROVIDE_LATITUDE);
        String latitude = inputValidator.retrievesString();
        System.out.println(PROVIDE_LONGITUDE);
        String longitude = inputValidator.retrievesString();
        System.out.println();
        String weatherEntry = locationController.createNewLocation(countryName, regionName, locationName, latitude,longitude);
    }

    private void readWeatherEntries() {
        String readWeatherLocation = locationController.readLocations();
        System.out.println();
        System.out.println("Retreived Locations:\n " + readWeatherLocation);
    }

    private void obtainWeatherForecast() {
        String obtainWeatherLocation = locationController.obtainLocation();
        System.out.println();
        System.out.println("Obtained Locations: " + obtainWeatherLocation);
    }
}
