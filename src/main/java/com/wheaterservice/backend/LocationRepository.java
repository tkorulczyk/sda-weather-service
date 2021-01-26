package com.wheaterservice.backend;

import java.util.List;

public interface LocationRepository {
    Location saveLocation(Location location);
    List<Location> readAllEntries();


}
