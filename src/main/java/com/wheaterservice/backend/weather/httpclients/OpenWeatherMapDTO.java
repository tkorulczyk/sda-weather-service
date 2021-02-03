package com.wheaterservice.backend.weather.httpclients;

import lombok.Data;

@Data
public class OpenWeatherMapDTO {
    private Main main;
    private Wind wind;

    @Data
    static class Main {
        private int temp;
        private int pressure;
        private int humidity;
    }

    @Data
    static class Wind {
        private int speed;
        private int deg;
    }


}
