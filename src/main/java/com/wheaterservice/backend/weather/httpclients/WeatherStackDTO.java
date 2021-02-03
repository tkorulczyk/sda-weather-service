package com.wheaterservice.backend.weather.httpclients;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherStackDTO {
    private Current current;

    @Data
    static class Current {
        @JsonProperty("temperature")
        public int getTemperature() {
            return this.temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        private int temperature;

        @JsonProperty("weather_descriptions")
        public List<String> getWeather_descriptions() {
            return this.weather_descriptions;
        }

        public void setWeather_descriptions(List<String> weather_descriptions) {
            this.weather_descriptions = weather_descriptions;
        }

        List<String> weather_descriptions;

        @JsonProperty("wind_speed")
        public int getWind_speed() {
            return this.wind_speed;
        }

        public void setWind_speed(int wind_speed) {
            this.wind_speed = wind_speed;
        }

        private int wind_speed;


        @JsonProperty("degrees")
        public int getDegrees() {
            return this.degrees;
        }
        private int degrees;


        @JsonProperty("pressure")
        public int getPressure() {
            return this.pressure;
        }
        private int pressure;

        @JsonProperty("humidity")
        public int getHumidity() {
            return this.humidity;
        }

        private int humidity;
    }
}
