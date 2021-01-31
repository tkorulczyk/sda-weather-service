package com.wheaterservice.backend.weather.httpclients;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherStackDTO {
    private Current current;

    @Data
    static class Current{
        @JsonProperty("observation_time")
        public String getObservation_time() {
            return this.observation_time; }
        public void setObservation_time(String observation_time) {
            this.observation_time = observation_time; }
        String observation_time;
        @JsonProperty("temperature")
        public int getTemperature() {
            return this.temperature; }
        public void setTemperature(int temperature) {
            this.temperature = temperature; }
        int temperature;
        @JsonProperty("weather_code")
        public int getWeather_code() {
            return this.weather_code; }
        public void setWeather_code(int weather_code) {
            this.weather_code = weather_code; }
        int weather_code;
        @JsonProperty("weather_descriptions")
        public List<String> getWeather_descriptions() {
            return this.weather_descriptions; }
        public void setWeather_descriptions(List<String> weather_descriptions) {
            this.weather_descriptions = weather_descriptions; }
        List<String> weather_descriptions;
        @JsonProperty("wind_speed")
        public int getWind_speed() {
            return this.wind_speed; }
        public void setWind_speed(int wind_speed) {
            this.wind_speed = wind_speed; }
        int wind_speed;
        @JsonProperty("wind_degree")
        public int getWind_degree() {
            return this.wind_degree; }
        public void setWind_degree(int wind_degree) {
            this.wind_degree = wind_degree; }
        int wind_degree;
        @JsonProperty("wind_dir")
        public String getWind_dir() {
            return this.wind_dir; }
        public void setWind_dir(String wind_dir) {
            this.wind_dir = wind_dir; }
        String wind_dir;
        @JsonProperty("pressure")
        public int getPressure() {
            return this.pressure; }
        public void setPressure(int pressure) {
            this.pressure = pressure; }
        int pressure;
        @JsonProperty("precip")
        public double getPrecip() {
            return this.precip; }
        public void setPrecip(double precip) {
            this.precip = precip; }
        double precip;
        @JsonProperty("humidity")
        public int getHumidity() {
            return this.humidity; }
        public void setHumidity(int humidity) {
            this.humidity = humidity; }
        int humidity;
        @JsonProperty("cloudcover")
        public int getCloudcover() {
            return this.cloudcover; }
        public void setCloudcover(int cloudcover) {
            this.cloudcover = cloudcover; }
        int cloudcover;
        @JsonProperty("feelslike")
        public int getFeelslike() {
            return this.feelslike; }
        public void setFeelslike(int feelslike) {
            this.feelslike = feelslike; }
        int feelslike;
        @JsonProperty("uv_index")
        public int getUv_index() {
            return this.uv_index; }
        public void setUv_index(int uv_index) {
            this.uv_index = uv_index; }
        int uv_index;
        @JsonProperty("visibility")
        public int getVisibility() {
            return this.visibility; }
        public void setVisibility(int visibility) {
            this.visibility = visibility; }
        int visibility;
        @JsonProperty("is_day")
        public String getIs_day() {
            return this.is_day; }
        public void setIs_day(String is_day) {
            this.is_day = is_day; }
        String is_day;
    }
}


/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */

