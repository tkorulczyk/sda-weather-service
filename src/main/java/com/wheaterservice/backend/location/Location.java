package com.wheaterservice.backend.location;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
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

    public Location(String countryName, String regionName, String cityName, float latitude, float longitude) {
        this.countryName = countryName;
        this.regionName = regionName;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
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
