package com.wheaterservice.backend;

import java.util.List;

public class LocationService {
    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location createNewLocation(String countryName, String regionName, String cityName, String latitude, String longitude) {
        float flatitude;
        float flongitude;

        if (countryName == null || regionName == null || cityName == null) {
            throw new IllegalArgumentException("Name of Country, Region and City cannot be null");
        }

        if (countryName.isBlank() || regionName.isBlank() || cityName.isBlank()) {
            throw new IllegalArgumentException("Name of Country, Region and City cannot be empty");
        }

        if (!countryName.matches("[a-zA-Z]") || !regionName.matches("[a-zA-Z]")
                || !cityName.matches("[a-zA-Z]")) {
            throw new IllegalArgumentException("Name of Country, Region and City should be only letters");
        }


        if (latitude.matches("-?[0-9]{1,2}[,.]?[0-9]{0,4}")) {
            String latitudeR = latitude.replace(',', '.');
            flatitude = Float.parseFloat(latitudeR);

            if (flatitude < -90 || flatitude > 90) {
                throw new IllegalArgumentException("Latitude value exeeded accepted ragne");
            }

        } else {
            throw new IllegalArgumentException("Latitude can only consist of numbers");
        }

        if (longitude.matches("-?[0-9]{1,2}[,.]?[0-9]{0,4}")) {
            String longitudeR = longitude.replace(',', '.');
            flongitude = Float.parseFloat(longitudeR);

            if (flongitude < -90 || flongitude > 90) {
                throw new IllegalArgumentException("Longitude value exeeded accepted ragne");
            }
        } else {
            throw new IllegalArgumentException("Longitude can only consist of numbers");
        }


        Location location = new Location(countryName, regionName, cityName, flatitude, flongitude);
        return locationRepository.saveLocation(location);
    }

    public List<Location> readLocations() {
        return locationRepository.readAllEntries();
    }

}

