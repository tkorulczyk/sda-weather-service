package com.wheaterservice.backend.location;

import java.util.List;

public class LocationService {

    private LocationRepository locationRepository;
    private DatabaseInputValidator dbValidator;


    public LocationService(LocationRepository locationRepository, DatabaseInputValidator dbValidator) {
        this.locationRepository = locationRepository;
        this.dbValidator = dbValidator;
    }

    public Location createNewLocation(String countryName, String regionName, String cityName, String latitude, String longitude) { // todo use package-scope
        if (dbValidator.isLocationParameterNull(cityName, regionName, cityName)) {
            throw new IllegalArgumentException("Name of Country, Region and City cannot be null");
        }

        if (dbValidator.isLocationParameterBlank(cityName, regionName, cityName)) {
            throw new IllegalArgumentException("Name of Country, Region and City cannot be empty");
        }

        if (dbValidator.isNotLocationMatchesRegexPattern(cityName, regionName, cityName)) {
            throw new IllegalArgumentException("Name of Country, Region and City should be only letters");
        } else {
            convertToTitleCaseIteratingChars(cityName, regionName, cityName);
        }

        float flatitude = 0f;
        if (dbValidator.isNotLatitudeMatchesRegexPattern(latitude)) {
            flatitude = dbValidator.parseStringToFloat(latitude);
            if (flatitude < -90 || flatitude > 90) {
                throw new IllegalArgumentException("Latitude value exceeded accepted range");
            }
        }

        float flongitude = 0f;
        if (dbValidator.isNotLongitudeMatchesRegexPattern(longitude)) {
            flongitude = dbValidator.parseStringToFloat(latitude);
            if (flongitude < -180 || flongitude > 180) {
                throw new IllegalArgumentException("Longitude value exceeded accepted range");
            }
        }

        Location location = new Location(countryName, regionName, cityName, flatitude, flongitude);
        return locationRepository.saveLocation(location);
    }


     List<Location> readLocations() {
        return locationRepository.readAllEntries();
    }



    public String convertToTitleCaseIteratingChars(String... args) {
         StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (int i = 0; i < args.length; i++) {
            for (char ch : args[i].toCharArray()) {
                if (Character.isSpaceChar(ch)) {
                    convertNext = true;
                } else if (convertNext) {
                    ch = Character.toTitleCase(ch);
                    convertNext = false;
                } else {
                    ch = Character.toLowerCase(ch);
                }
                converted.append(ch);
            }


        }
        return args.toString();
    }
}


