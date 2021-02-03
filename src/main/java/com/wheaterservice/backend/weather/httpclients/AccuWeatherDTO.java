package com.wheaterservice.backend.weather.httpclients;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */
@Data
public class AccuWeatherDTO {
    private DailyForecasts[] dailyForecast;

    @Data
    static class DailyForecasts {

    }

}

