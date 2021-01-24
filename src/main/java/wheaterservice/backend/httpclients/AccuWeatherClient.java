package wheaterservice.backend.httpclients;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AccuWeatherClient {
    private final ObjectMapper objectMapper;

    public AccuWeatherClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getCurrentWeatherForecast() {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://dataservice.accuweather.com/forecasts/v1/hourly/12hour/{locationKey})"))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();

            AccuWeatherDTO accuWeatherDTO = objectMapper.readValue(responseBody, AccuWeatherDTO.class);
            // to do
            return null;

        } catch (Exception e) {
            System.out.println("Cannot retrieve wheather unit from AccuWheater " + e.getMessage());
            return null;
        }

    }


}
