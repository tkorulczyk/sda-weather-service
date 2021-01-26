package com.wheaterservice.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseInputValidatorTest {
    private DatabaseInputValidator databaseInputValidator;

    @BeforeEach
    void setUp() {
        databaseInputValidator = new DatabaseInputValidator();
    }


    @Test
    void thatLocationParameterIsNull() {
        // when
        String str = null;
        // then
        Assertions.assertNotNull(databaseInputValidator.isLocationParameterNull(str));
    }

    @Test
    void thatLocationParameterIsBlank() { // I don't know how to test if parameter is blank
        // when
        String str = " ";
        // then
     //   Assertions.assert(databaseInputValidator.isLocationParameterBlank(str));
    }


    // ===================================================================================
    // LOCATION REGEX PATTERN TESTS
    // ===================================================================================

    @Test
    void thatLocationParameterDoesNotMatchNumbersAndLettersRegexPattern() {
        // when
        String str = "3231xc";
        // then
           Assertions.assertTrue(databaseInputValidator.isNotLocationMatchesRegexPattern(str));
    }

    @Test
    void thatLocationParameterDoesNotMatchOnlyNumbersRegexPattern() {
        // when
        String str = "3231";
        // then
        Assertions.assertTrue(databaseInputValidator.isNotLocationMatchesRegexPattern(str));
    }

    @Test
    void thatLocationParameterDoesNotMatchWhitespacesRegexPattern() {
        // when
        String str = "    ";
        // then
        Assertions.assertTrue(databaseInputValidator.isNotLocationMatchesRegexPattern(str));
    }

    @Test
    void thatLocationParameterDoesNotMatchWildcardsRegexPattern() {
        // when
        String str = "$^*()#";
        // then
        Assertions.assertTrue(databaseInputValidator.isNotLocationMatchesRegexPattern(str));
    }


    // Tests to verity if Regex Pattern for Location Accept only letters
    @Test
    void thatLocationParameterMatchesOnlyLowercaseLettersRegexPattern() {
        // when
        String str = "sdfsdf";
        // then
        Assertions.assertFalse(databaseInputValidator.isNotLocationMatchesRegexPattern(str));
    }

    @Test
    void thatLocationParameterMatchesOnlyUppercaseLettersRegexPattern() {
        // when
        String str = "CHADAT";
        // then
        Assertions.assertFalse(databaseInputValidator.isNotLocationMatchesRegexPattern(str));
    }


    // ===================================================================================
    // COORDINATE REGEX PATTERN TESTS
    // ===================================================================================

    @Test
    void thatCoordinateDoesNotMatchlettersRegexPattern() {
        // when
        String str = "dsdf";
        // then
        Assertions.assertTrue(databaseInputValidator.isNotCoordinateMatchesRegexPattern(str));
    }

    @Test
    void thatCoordinateDoesNotMatchLettersAndNumbersRegexPattern() {
        // when
        String str = "342dsdf";
        // then
        Assertions.assertTrue(databaseInputValidator.isNotCoordinateMatchesRegexPattern(str));
    }

    @Test
    void thatCoordinateDoesNotMatchNumberWithThreeDigitsBeforeDotRegexPattern() {
        // when
        String str = "346.234";
        // then
        Assertions.assertTrue(databaseInputValidator.isNotCoordinateMatchesRegexPattern(str));
    }


    @Test
    void thatCoordinateDoesNotMatchNumberWithThreeDigitsRegexPattern() {
        // when
        String str = "911";
        // then
        Assertions.assertTrue(databaseInputValidator.isNotCoordinateMatchesRegexPattern(str));
    }

    @Test
    void thatCoordinateDoesNotMatchNumberWithoutPrefixPartRegexPattern() {
        // when
        String str = ".89";
        // then
        Assertions.assertTrue(databaseInputValidator.isNotCoordinateMatchesRegexPattern(str));
    }

}