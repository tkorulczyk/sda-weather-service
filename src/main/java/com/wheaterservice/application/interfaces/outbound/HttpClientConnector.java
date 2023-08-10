package com.wheaterservice.application.interfaces.outbound;

import java.util.Map;

public interface HttpClientConnector {
  String initializeHttpConnection(String url);

  void setHeaders(Map<String, String> headers);

  String initializeHttpConnectionWithPayload(String url, String payload);
}