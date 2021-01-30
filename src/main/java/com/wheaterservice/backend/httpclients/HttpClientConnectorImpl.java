package com.wheaterservice.backend.httpclients;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientConnectorImpl implements HttpClientConnector {

    @Override
    public String initializeHttpConnection(String URL) {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();
            return responseBody;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot initialize http connection");
            return null;
        }
    }
}
