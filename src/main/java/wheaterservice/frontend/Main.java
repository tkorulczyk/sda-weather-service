package wheaterservice.frontend;

import wheaterservice.backend.WeatherEntryController;

public class Main {


    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface(new WeatherEntryController());
        userInterface.showInitialMenu();

    }
}
