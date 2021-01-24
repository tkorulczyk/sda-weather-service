package com.wheaterservice.backend;

import java.util.List;

public class LocationService {
    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location createNewLocation(String countryName, String regionName, String cityName, String latitude, String longitude) {
        // to do backend walidacja

        Location location = new Location(countryName,regionName,cityName,latitude,longitude);
        return locationRepository.saveLocation(location);
    }

    public List<Location> readLocations() {
        return null;
    }


}
