package com.wheaterservice.application.utils;

public class DatabaseInputValidator {

    public boolean isLocationParameterNull(String... args) {
        for (String arg : args) {
            if (arg == null) {
                return true;
            }
        }
        return false;
    }

    public boolean isLocationParameterBlank(String... args) {
        for (String arg : args) {
            if (arg.isBlank()) {
                return true;
            }
        }
        return false;
    }

    public boolean isNotLocationMatchesRegexPattern(String... args) {
        for (String arg : args) {
            if (!arg.matches("[a-zA-Z]+")) {
                return true;
            }
        }
        return false;
    }

    public boolean isNotLatitudeMatchesRegexPattern(String... args) {
        for (String arg : args) {
            if (!arg.matches("-?[0-9]?[0-9]([,.][0-9]{0,4})?")) {
                return true;
            }
        }
        return false;
    }

    public boolean isNotLongitudeMatchesRegexPattern(String... args) {
        for (String arg : args) {
            if (!arg.matches("-?[0-9]?[0-9]?[0-9]([,.][0-9]{0,4})?")) {
                return true;
            }
        }
        return false;
    }

    public float parseStringToFloat(String string) {
        String stringWithReplacedComma = string.replace(',', '.');
        return Float.parseFloat(stringWithReplacedComma);
    }

}
