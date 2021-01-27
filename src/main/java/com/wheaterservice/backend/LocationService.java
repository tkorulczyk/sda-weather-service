package com.wheaterservice.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wheaterservice.backend.httpclients.AccuWeatherClient;

import java.util.List;

public class LocationService {
    private final LocationRepository locationRepository;
    private DatabaseInputValidator dbValidator = new DatabaseInputValidator();
    private LocationRepository locationRepository;
    private AccuWeatherClient accuWeatherClient = new AccuWeatherClient(new ObjectMapper());


    private final LocationRepository locationRepository;
    private DatabaseInputValidator dbValidator = new DatabaseInputValidator();

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    public Location createNewLocation(String countryName, String regionName, String cityName, String latitude, String longitude) {

        if (dbValidator.isLocationParameterNull(cityName, regionName, cityName)) {
            throw new IllegalArgumentException("Name of Country, Region and City cannot be null");
        }

        if (dbValidator.isLocationParameterBlank(cityName, regionName, cityName)) {
            throw new IllegalArgumentException("Name of Country, Region and City cannot be empty");
        }

        if (dbValidator.isNotLocationMatchesRegexPattern()) {
            throw new IllegalArgumentException("Name of Country, Region and City should be only letters");
        }

        float flatitude = 0f;
        if (dbValidator.isNotCoordinateMatchesRegexPattern(latitude)) {
            flatitude = dbValidator.parseStringToFloat(latitude);
            if (flatitude < -90 || flatitude > 90) {
                throw new IllegalArgumentException("Latitude value exceeded accepted range");
            }
        }

        float flongitude = 0f;
        if (dbValidator.isNotCoordinateMatchesRegexPattern(longitude)) {
            flongitude = dbValidator.parseStringToFloat(latitude);
            if (flongitude < -90 || flongitude > 90) {
                throw new IllegalArgumentException("Longitude value exceeded accepted range");
            }
        }

        Location location = new Location(countryName, regionName, cityName, flatitude, flongitude);
        return locationRepository.saveLocation(location);
    }


    public List<Location> readLocations() {
        return locationRepository.readAllEntries();
    }


    public String getWeatherForecast() {

       return accuWeatherClient.getWeatherForecast();
    }
}


