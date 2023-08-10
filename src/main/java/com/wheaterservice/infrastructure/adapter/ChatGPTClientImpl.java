package com.wheaterservice.infrastructure.adapter;

import com.wheaterservice.application.dto.ChatGPTResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wheaterservice.application.interfaces.outbound.HttpClientConnector;
import lombok.RequiredArgsConstructor;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 10.08.2023
 *
 * @author Tomasz Korulczyk
 */

@RequiredArgsConstructor
public class ChatGPTClientImpl implements ChatGPTClient {

  private static final String BASE_CONFIG_FILE = "application.yaml";
  private static String CONFIG_FILE;

  static {
    Yaml yaml = new Yaml();
    InputStream inputStream = ChatGPTClientImpl.class.getClassLoader().getResourceAsStream(BASE_CONFIG_FILE);
    Map<String, Object> obj = yaml.load(inputStream);

    Object appObj = obj.get("app");
    if (appObj instanceof Map) {
      @SuppressWarnings("unchecked")
      Map<String, Object> appMap = (Map<String, Object>) appObj;
      String profile = (String) appMap.get("profiles.active");
      CONFIG_FILE = "application-" + profile + ".yaml";
    } else {
      throw new RuntimeException("Incorrect configuration structure: 'app' should be map.");
    }
  }
  private String API_URL;
  private String API_KEY;

  private final HttpClientConnector httpClientConnector;
  private final ObjectMapper objectMapper;

  {
    initializeConfigValues();
  }

  private void initializeConfigValues() {
    Yaml yaml = new Yaml();
    InputStream inputStream = ChatGPTClientImpl.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
    Map<String, Object> obj = yaml.load(inputStream);

    Object chatgptObj = obj.get("chatgpt");
    if (chatgptObj instanceof Map) {
      Map<String, Object> chatgptMap = (Map<String, Object>) chatgptObj;
      this.API_URL = (String) chatgptMap.get("api-url");
      this.API_KEY = (String) chatgptMap.get("api-key");
    } else {
      throw new RuntimeException("Incorrect configuration structure: 'chatgpt' should be map.");
    }
  }

  @Override
  public String fetchResponse(String input) {
    String payload = createPayload(input);

    // Set HTTP headers
    Map<String, String> headers = new HashMap<>();
    headers.put("Authorization", "Bearer " + API_KEY);
    headers.put("Content-Type", "application/json");
    httpClientConnector.setHeaders(headers);

    String responseBody = httpClientConnector.initializeHttpConnectionWithPayload(API_URL, payload);

    try {
      ChatGPTResponseDTO response = objectMapper.readValue(responseBody, ChatGPTResponseDTO.class);
      // Extract the message content from the nested structure of ChatGPTResponseDTO
      return response.getChoices().get(0).getMessage().getContent();
    } catch (Exception e) {
      throw new RuntimeException("Error while processing responses from ChatGPT.", e);
    }
  }

  private String createPayload(String input) {
    Map<String, Object> payload = new HashMap<>();
    payload.put("model", "gpt-3.5-turbo");
    payload.put("messages", new Object[]{new HashMap<String, String>() {{
      put("role", "user");
      put("content", input);
    }}});
    payload.put("temperature", 0.8);

    try {
      return objectMapper.writeValueAsString(payload);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error while creating payload for ChatGPT.", e);
    }
  }
}
