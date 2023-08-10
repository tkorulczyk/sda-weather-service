package com.wheaterservice.application.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Created on 10.08.2023
 *
 * @author Tomasz Korulczyk
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTResponseDTO {
  private String id;
  private String object;
  private long created;
  private String model;
  private List<Choice> choices;
  private Usage usage;

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Choice {
    private int index;
    private Message message;

    @JsonProperty("finish_reason")
    private String finishReason;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Message {
    private String role;
    private String content;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Usage {
    @JsonProperty("prompt_tokens")
    private int promptTokens;

    @JsonProperty("completion_tokens")
    private int completionTokens;

    @JsonProperty("total_tokens")
    private int totalTokens;
  }
}