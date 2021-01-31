package com.wheaterservice.backend.location;

import com.wheaterservice.backend.location.Location;
import com.wheaterservice.backend.location.LocationRepository;

import java.util.List;

public class LocationRepositoryMock implements LocationRepository {

    @Override
    public Location saveLocation(Location location) {
        return location;
    }

    @Override
    public List<Location> readAllEntries() {
        return null;
    }
}
