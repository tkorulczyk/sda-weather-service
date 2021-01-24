package wheaterservice.frontend; // todo change package path by adding com.

import wheaterservice.backend.WeatherEntryController;
import wheaterservice.backend.WeatherEntryRepository;
import wheaterservice.backend.WeatherEntryService;

public class Main {

    public static void main(String[] args) {
        WeatherEntryRepository weatherEntryRepository = new WeatherEntryRepository();
        WeatherEntryService weatherEntryService = new WeatherEntryService(weatherEntryRepository);
        WeatherEntryController weatherEntryController = new WeatherEntryController(weatherEntryService);

        UserInterface userInterface = new UserInterface(weatherEntryController);
        userInterface.showInitialMenu();
    }
}
