package com.wheaterservice.backend.location;

import com.wheaterservice.backend.weather.WeatherService;

import java.util.List;

public class LocationController {

    private final LocationService locationService;
    private final WeatherService weatherService;
    private Location newLocation;

    public LocationController(LocationService locationService, WeatherService weatherService) {
        this.locationService = locationService;
        this.weatherService = weatherService;
    }


    public String createNewLocation(String countryName, String regionName, String cityName, String latitude, String longitude) {
      try {
          newLocation = locationService.createNewLocation(countryName, regionName, cityName, latitude, longitude);
      } catch (IllegalArgumentException e) {
          return ("An error occurred while trying to add a new location" + e.getMessage());
      }


        return newLocation.toString();
    }

    public String readLocations() {
        List<Location> locations = locationService.readLocations();
        return locations.toString();
    }

}
