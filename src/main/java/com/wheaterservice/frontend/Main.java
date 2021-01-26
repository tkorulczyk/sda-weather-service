package com.wheaterservice.frontend;

import com.wheaterservice.backend.LocationController;
import com.wheaterservice.backend.LocationRepositoryImpl;
import com.wheaterservice.backend.LocationService;

public class Main {

    public static void main(String[] args) {

        LocationRepositoryImpl locationRepository = new LocationRepositoryImpl();
        LocationService locationService = new LocationService(locationRepository);
        LocationController locationController = new LocationController(locationService);

        UserInterface userInterface = new UserInterface(locationController);
        userInterface.showInitialMenu();
    }
}
