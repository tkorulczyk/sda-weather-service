package com.wheaterservice.backend.httpclients;

public class WheaterParamDTO {
    private String Temperature;
    private String WindGust;
    private String Direction;


    public WheaterParamDTO() {
    }

    public WheaterParamDTO(String temperature, String windGust, String direction) {
        Temperature = temperature;
        WindGust = windGust;
        Direction = direction;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getWindGust() {
        return WindGust;
    }

    public void setWindGust(String windGust) {
        WindGust = windGust;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }
}
