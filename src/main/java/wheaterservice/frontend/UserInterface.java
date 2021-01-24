package wheaterservice.frontend;

import wheaterservice.backend.WeatherEntryController;

public class UserInterface {
    private InputValidator inputValidator = new InputValidator();
    private final WeatherEntryController weatherEntryController;

    private  final String CLOSED_APP_MESSAGE = "\nThank you for your time! \n" + "Good bye!";
    private final String PROVIDE_LOCATION_NAME = "Please provide location name";
    private final String PROVIDE_REGION_NAME = "Please provide region name";
    private final String PROVIDE_COUNTY_NAME = "Please provide country name";
    private final String PROVIDE_LATITUDE = "Please provide latitude of the location";
    private final String INVITATION_MESSAGE = "\n" +
            "========================================================== \n" +
            "Welcome to the ⌂ weather service ⌂, what would you like to do? \n" +
            "========================================================== \n" + "\n" +
            Colors.BLUE +"1 => Add new location \n" +
            Colors.BLUE +"2 => Read existing weather entries \n" +
            Colors.BLUE +"2 => Obtain a weather forecast \n" +
            Colors.RED + "4 => Close the application \n" + Colors.RESET +
            Colors.RESET + "↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ";



    public UserInterface(WeatherEntryController weatherEntryController) {
        this.weatherEntryController = weatherEntryController;
    }


    void showInitialMenu() {


        while(true) {
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
        String weatherEntry = weatherEntryController.createNewWeatherLocation(countryName,regionName,locationName,latitude);
    }

    private void readWeatherEntries() {
        String readWeatherLocation = weatherEntryController.readWeatherLocation();

    }

    private void obtainWeatherForecast() {
    }




}
