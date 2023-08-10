package com.wheaterservice.infrastructure.adapter;

import com.wheaterservice.application.interfaces.outbound.HttpClientConnector;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpClientConnectorImpl implements HttpClientConnector {

    private static final String CONTENT_TYPE = "application/json; charset=utf-8";

    private final HttpClient httpClient;
    private Map<String, String> headers = new HashMap<>();

    public HttpClientConnectorImpl() {
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public String initializeHttpConnection(String URL) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
            .POST(HttpRequest.BodyPublishers.noBody()) // POST with no body
            .uri(URI.create(URL));

        // Set headers to the request
        headers.forEach(requestBuilder::header);

        HttpRequest httpRequest = requestBuilder.build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return httpResponse.body();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot initialize http connection");
            return null;
        }
    }

    @Override
    public String initializeHttpConnectionWithPayload(String URL, String payload) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
            .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
            .uri(URI.create(URL))
            .header("Content-Type", CONTENT_TYPE);

        // Set additional headers to the request
        headers.forEach(requestBuilder::header);

        HttpRequest httpRequest = requestBuilder.build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return httpResponse.body();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot initialize http connection with payload");
            return null;
        }
    }
}
