package com.wheaterservice.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherStackDTO {
    private Current current;

    @Data
    public static class Current {
        @JsonProperty("temperature")
        private float temperature;

        @JsonProperty("weather_descriptions")
        List<String> weatherDescription;

        @JsonProperty("wind_speed")
        private float windSpeed;

        @JsonProperty("wind_degree")
        private float windDir;

        @JsonProperty("pressure")
        private float pressure;

        @JsonProperty("humidity")
        private float humidity;

        @JsonProperty("cloudcover")
        private float cloudCover;

        @JsonProperty("feelslike")
        private float feelsLike;

        @JsonProperty("uv_index")
        private int uvIndex;

        @JsonProperty("visibility")
        private float visibility;
    }
}
