package com.wheaterservice.backend.weather.httpclients;

import lombok.Data;

import java.util.Date;

@Data
public class WeatherParamDTO {
    private DailyForecasts dailyForecasts;

    @Data
    static class DailyForecasts {
        private Date date;
    }
}
