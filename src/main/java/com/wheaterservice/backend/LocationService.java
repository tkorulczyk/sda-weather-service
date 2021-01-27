package com.wheaterservice.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wheaterservice.backend.httpclients.AccuWeatherClient;

import java.util.List;

public class LocationService {
    private LocationRepository locationRepository;
    private AccuWeatherClient accuWeatherClient = new AccuWeatherClient(new ObjectMapper());



    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location createNewLocation(String countryName, String regionName, String cityName, String latitude, String longitude) {
        // to do backend walidacja

        Location location = new Location(countryName,regionName,cityName,latitude,longitude);
        return locationRepository.saveLocation(location);
    }

    public List<Location> readLocations() {
        return locationRepository.readAllEntries();
    }


    public String getWeatherForecast() {

       return accuWeatherClient.getWeatherForecast();
    }
}
