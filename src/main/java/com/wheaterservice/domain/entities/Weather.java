package com.wheaterservice.domain.entities;


import com.wheaterservice.application.utils.Utils;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@Data
@Entity
@NoArgsConstructor
@Table(name = "WEATHER")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;

    private String location;
    private float temperature;
    private float windSpeed;
    private float windDir;
    private float airPressure;
    private float humidity;
    private float cloudCover;
    private float feelsLike;
    private int uvIndex;
    private float visibility;
    private String weatherDescription;
    private String weatherServiceType;

    @Builder
    public Weather( final LocalDate date, final String location, final float temperature, final float windSpeed, final float windDir, final float airPressure, final float humidity, final float cloudCover, final float feelsLike, final int uvIndex, final float visibility, final String weatherDescription, final String weatherServiceType) {
        this.date = date;
        this.location = location;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDir = windDir;
        this.airPressure = airPressure;
        this.humidity = humidity;
        this.cloudCover = cloudCover;
        this.feelsLike = feelsLike;
        this.uvIndex = uvIndex;
        this.visibility = visibility;
        this.weatherDescription = weatherDescription;
        this.weatherServiceType = weatherServiceType;
    }



    @Override
    public String toString() {
        String cityName = (location != null) ? location : "Unknown Location";
        String arrow = Utils.arrowDrawer(windDir);

        return new StringBuilder()
            .append("Weather Record:")
            .append(System.lineSeparator())
            .append("Location: ").append(cityName.toUpperCase())
            .append(System.lineSeparator())
            .append("Temperature: ").append(temperature).append("°C")
            .append(System.lineSeparator())
            .append("Pressure: ").append(airPressure).append(" hPa")
            .append(System.lineSeparator())
            .append("Humidity: ").append(humidity).append("%")
            .append(System.lineSeparator())
            .append("Wind Speed: ").append(windSpeed).append(" m/s")
            .append(System.lineSeparator())
            .append("Wind Direction: ").append(windDir).append("° ").append(arrow)
            .append(System.lineSeparator())
            .append("Cloud Cover: ").append(cloudCover).append("%")
            .append(System.lineSeparator())
            .append("Feels Like: ").append(feelsLike).append("°C")
            .append(System.lineSeparator())
            .append("UV Index: ").append(uvIndex)
            .append(System.lineSeparator())
            .append("Visibility: ").append(visibility).append(" km")
            .append(System.lineSeparator())
            .append("Description: ").append(weatherDescription)
            .toString();
    }

}
