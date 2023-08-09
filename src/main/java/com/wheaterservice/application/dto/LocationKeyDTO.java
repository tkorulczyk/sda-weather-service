package com.wheaterservice.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationKeyDTO {
    @JsonProperty("Key")
    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    String key;
}