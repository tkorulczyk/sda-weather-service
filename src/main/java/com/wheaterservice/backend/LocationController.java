package com.wheaterservice.backend;

import java.util.List;

public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    public String createNewLocation(String countryName, String regionName, String cityName, String latitude, String longitude) {
        Location newLocation = locationService.createNewLocation(countryName, regionName, cityName, latitude, longitude);
        return newLocation.toString();
    }

    public String readLocations() {
        List<Location> locations = locationService.readLocations();
        return locations.toString();
    }

    public String getWeatherForecast() {

        return locationService.getWeatherForecast();

    }
}
