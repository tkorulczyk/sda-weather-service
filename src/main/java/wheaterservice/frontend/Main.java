package wheaterservice.frontend;

import wheaterservice.backend.WeatherEntryController;
import wheaterservice.backend.WeatherEntryRepository;
import wheaterservice.backend.WeatherEntryService;

public class Main {


    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface(new WeatherEntryController(new WeatherEntryService(new WeatherEntryRepository())));
        userInterface.showInitialMenu();

    }
}
