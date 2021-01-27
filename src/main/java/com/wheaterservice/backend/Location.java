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
    private float latitude;
    private float longitude;

    public Location() {
    }

    public Location(String countryName, String regionName, String cityName, float latitude, float longitude) {
        this.countryName = countryName;
        this.regionName = regionName;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return  "\n" + "Location {" +
                "id=" + id +
                " | countryName='" + countryName + '\'' +
                " | regionName='" + regionName + '\'' +
                " | cityName='" + cityName + '\'' +
                " | latitude='" + latitude + '\'' +
                " | longitude='" + longitude + '\'' +
                '}';
    }
}
