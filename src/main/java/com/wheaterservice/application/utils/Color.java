package com.wheaterservice.application.utils;

public enum Color {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    BLUE("\u001B[34m");

    private final String code;

    Color(String code) {
        this.code = code;
    }

    public String get() {
        return code;
    }
}
