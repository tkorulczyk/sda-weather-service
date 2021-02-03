package com.wheaterservice.backend.weather;


import com.wheaterservice.backend.location.Location;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Data
@Entity
@RequiredArgsConstructor
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Location location;
    // AccuWeather variables
    private int AWTemperature;
    private int AWwindSpeed;
    private int AWwindDir;
    private int AWpressure;
    private int AWhumidity;

    // Open Weather Map variables
    private int OWMtemperature;
    private int OWMwindSpeed;
    private int OWMwindDir;
    private int OWMpressure;
    private int OWMhumidity;
    // Weather Stack variables
    private int WStemperature;
    private int WSwindSpeed;
    private int WSwindDir;
    private int WSpressure;
    private int WShumidity;
    // Average variables
    private int AVGtemperature;
    private int AVGWindSpeed;
    private int AVGWindDir;
    private int AVGpressure;
    private int AVGhumidity;

    public Weather() {
    }

    public Weather(LocalDate date, Location location, int AWTemperature, int AWwindSpeed, int AWwindDir, int AWpressure, int AWhumidity, int OWMtemperature, int OWMwindSpeed, int OWMwindDir, int OWMpressure, int OWMhumidity, int WStemperature, int WSwindSpeed, int WSwindDir, int WSpressure, int WShumidity, int AVGtemperature, int AVGWindSpeed, int AVGWindDir, int AVGpressure, int AVGhumidity) {
        this.date = date;
        this.location = location;
        this.AWTemperature = AWTemperature;
        this.AWwindSpeed = AWwindSpeed;
        this.AWwindDir = AWwindDir;
        this.AWpressure = AWpressure;
        this.AWhumidity = AWhumidity;
        this.OWMtemperature = OWMtemperature;
        this.OWMwindSpeed = OWMwindSpeed;
        this.OWMwindDir = OWMwindDir;
        this.OWMpressure = OWMpressure;
        this.OWMhumidity = OWMhumidity;
        this.WStemperature = WStemperature;
        this.WSwindSpeed = WSwindSpeed;
        this.WSwindDir = WSwindDir;
        this.WSpressure = WSpressure;
        this.WShumidity = WShumidity;
        this.AVGtemperature = AVGtemperature;
        this.AVGWindSpeed = AVGWindSpeed;
        this.AVGWindDir = AVGWindDir;
        this.AVGpressure = AVGpressure;
        this.AVGhumidity = AVGhumidity;
    }
}
