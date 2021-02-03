package com.wheaterservice.backend.weather.httpclients;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AccuWeatherClient {
    private ObjectMapper objectMapper;
    private HttpClientConnector httpClientConnector;
    private LocationKeyDTO locationKeyDTO;
    private AccuWeatherDTO accuWeatherDTO;
    private final String APIkey = "b4GBr5Mm1Y9Z8yeCXDIFNK2LhJSsH573";
    private boolean details = true;
    private boolean metrics = false;
    private String locationKey;
    private int timeRange = 5;
    private String language;
    private int temperature;
    private int WindSpeed;
    private int WindDir;
    private int pressure;
    private int humidity;


    public int getTemperature() {
        return temperature;
    }

    public int getWindSpeed() {
        return WindSpeed;
    }

    public int getWindDir() {
        return WindDir;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public AccuWeatherClient() {
    }
//http://dataservice.accuweather.com/forecasts/v1/daily/5day/274231?apikey=b4GBr5Mm1Y9Z8yeCXDIFNK2LhJSsH573&language=en-us&details=false&metric=false

    public AccuWeatherClient(ObjectMapper objectMapper, HttpClientConnector httpClientConnector, LocationKeyDTO locationKeyDTO, AccuWeatherDTO accuWeatherDTO) {
        this.objectMapper = objectMapper;
        this.httpClientConnector = httpClientConnector;
        this.locationKeyDTO = locationKeyDTO;
        this.accuWeatherDTO = accuWeatherDTO;
    }

    public void setLanguage(String language) {
        this.language = language;
    }



    public void getWeatherLocationKey(String language, String cityName) {
        String URL = String.format("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=%s&q=%s&language=%s&details=%s", APIkey,  cityName, language, details);
        String responseBody = httpClientConnector.initializeHttpConnection(URL);

        try {


            List<LocationKeyDTO> locationKeyDTO = Arrays.asList(objectMapper.readValue(responseBody, LocationKeyDTO[].class));
            String locationKey = locationKeyDTO.get(0).getKey();
            this.locationKey = locationKey;
            this.language = language;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot initialize connection to AccuWeather");

        }
    }





    public String getAccuWeatherForecast(String language, String cityName, LocalDate weatherDate) {
        String URL = String.format("http://dataservice.accuweather.com/forecasts/v1/daily/%sday/%s?apikey=%s&language=%s&details=%s&metric=%s", this.timeRange, locationKey, APIkey, language, details, metrics);
        String responseBody = httpClientConnector.initializeHttpConnection(URL);

        try {
            List<AccuWeatherDTO.DailyForecasts> dailyForecasts = Arrays.asList(objectMapper.readValue(responseBody, AccuWeatherDTO.DailyForecasts[].class));
            AccuWeatherDTO  accuWeatherDTO = objectMapper.readValue(responseBody, AccuWeatherDTO.class);
            String date = dailyForecasts.get(0).toString();
            System.out.println(date);


        //  String temperature = weatherParamDTO.getTemperature(); // to do

//            String forecastMessage = String.format("\nCurrent weather metrics for %s are \n" +
//                            "↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ " +
//                            "\n temperature: %s,\n pressure: %s,\n humidity: %s,\n wind speed: %s,\n wind direction: %s %s,\n weather description: %s",
//                    cityName.toUpperCase(), temperature, pressure, humidity, WindSpeed, WindDir, arrow, weatherDescription);



            return responseBody;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot initialize connection to AccuWeather");
            return null;
        }
    }
}
