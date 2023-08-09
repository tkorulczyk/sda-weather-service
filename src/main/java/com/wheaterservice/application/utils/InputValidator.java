package com.wheaterservice.application.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static com.wheaterservice.application.utils.Color.*;

public class InputValidator {

    private Scanner scanner = new Scanner(System.in);
    private static final String WARNING_ONLY_LETTERS = RED.get() + ">>> Please type letter only with first being Capital! <<<" + RESET.get();
    private static final String WARNING_LANGUAGE = RED.get() + ">>> Please type only two letters representing language <<<" + RESET.get();
    private static final String WARNING_SAVE = RED.get() + ">>> Please type only one letter Y or N <<<" + RESET.get();
    private static final String WARNING_COORDINATE_WITHIN_RANGE = RED.get() + ">>> Please type a number from -90 to 90! <<<" + RESET.get();
    private static final String WARNING_DATE_FORMAT = RED.get() + ">>> Please type a date in the format DD-MM-YYYY! <<<" + RESET.get();

    public int retrievesInteger() {
        while (!scanner.hasNextInt()) {
            System.out.println(RED.get() + " \nPlease enter a number using only the digits 1 to 4" + RESET.get());
            scanner.next();
        }
        return scanner.nextInt();
    }

    public String retrieveAndValidateCoordinates() {
        while (true) {
            String coordinate = scanner.next();
            if (coordinate.matches("-?[0-9]{1,2}[,.]?[0-9]{0,4}")) {
                String coordinateWithDot = coordinate.replace(',', '.');
                float fCoordinate = Float.parseFloat(coordinateWithDot);
                if (fCoordinate >= -90 && fCoordinate <= 90) {
                    return coordinate;
                } else {
                    System.out.println(WARNING_COORDINATE_WITHIN_RANGE);
                }
            } else {
                System.out.println(WARNING_COORDINATE_WITHIN_RANGE);
            }
        }
    }

    public String retrieveAndValidateLocation() {
        while (true) {
            String location = scanner.next();
            if (location.matches("[A-Z][a-zA-Z]+")) {
                return location;
            } else {
                System.out.println(WARNING_ONLY_LETTERS);
            }
        }
    }

    public String retrieveAndValidateDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while (true) {
            String date = scanner.next();
            try {
                LocalDate.parse(date, formatter);
                return date;
            } catch (DateTimeParseException e) {
                System.out.println(WARNING_DATE_FORMAT);
            }
        }
    }

    public String retrieveAndValidateLanguage() {
        while (true) {
            String language = scanner.next();
            if (language.matches("[a-z]{2}")) {
                return language;
            } else {
                System.out.println(WARNING_LANGUAGE);
            }
        }
    }

    public String retrieveAndValidateLetter(String regex) {
        while (true) {
            String language = scanner.next();
            if (language.matches(regex)) {
                return language;
            } else {
                System.out.println(WARNING_SAVE);
            }
        }
    }

}
