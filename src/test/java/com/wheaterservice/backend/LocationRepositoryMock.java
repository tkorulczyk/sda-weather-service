package com.wheaterservice.backend;

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
