package com.wheaterservice.infrastructure.adapter;

/**
 * Created on 10.08.2023
 *
 * @author Tomasz Korulczyk
 */


public interface ChatGPTClient {
  String fetchResponse(String input);
}
