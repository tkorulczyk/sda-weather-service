package com.wheaterservice.backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String countryName;
    private String regionName;
    private String cityName;
    private String latitude;
    private String longitude;

    public Location() {
    }

    public Location(String countryName, String regionName, String cityName, String latitude, String longitude) {
        this.countryName = countryName;
        this.regionName = regionName;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
