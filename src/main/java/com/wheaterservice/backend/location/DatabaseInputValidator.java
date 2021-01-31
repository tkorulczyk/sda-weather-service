package com.wheaterservice.backend.location;

public class DatabaseInputValidator {

    boolean isLocationParameterNull(String... args) {
        for (String arg : args) {
            if (arg == null) {
                return true;
            }
        }
        return false;
    }

    boolean isLocationParameterBlank(String... args) {
        for (String arg : args) {
            if (arg.isBlank()) {
                return true;
            }
        }
        return false;
    }

    boolean isNotLocationMatchesRegexPattern(String... args) {
        for (String arg : args) {
            if (!arg.matches("[a-zA-Z]+")) {
                return true;
            }
        }
        return false;
    }

    boolean isNotLatitudeMatchesRegexPattern(String... args) {
        for (String arg : args) {
            if (!arg.matches("-?[0-9]?[0-9]([,.][0-9]{0,4})?")) {
                return true;
            }
        }
        return false;
    }

    boolean isNotLongitudeMatchesRegexPattern(String... args) {
        for (String arg : args) {
            if (!arg.matches("-?[0-9]?[0-9]?[0-9]([,.][0-9]{0,4})?")) {
                return true;
            }
        }
        return false;
    }

    float parseStringToFloat(String string) {
        String stringWithReplacedComma = string.replace(',', '.');
        return Float.parseFloat(stringWithReplacedComma);
    }

}
