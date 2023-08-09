package com.wheaterservice.unit;

import com.wheaterservice.application.utils.DatabaseInputValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseInputValidatorTest {
    private static DatabaseInputValidator databaseInputValidator;

    @BeforeAll
    static void setUp() {
        databaseInputValidator = new DatabaseInputValidator();
    }


    @Test
    void thatLocationParameterIsNull() { // todo action_criteria_result -> isLocationParameterNull_whenPassedParamIsNull_returnsTrue
        // when
        // String str = null;
        // then
        // Assertions.assertNotNull(databaseInputValidator.isLocationParameterNull(str));

//         todo
//          given - data preparation
//          then - perform an action
//          then - assertions

        // given
//        String arg = null;
//
//        // when
//        boolean result = databaseInputValidator.isLocationParameterNull(arg);
//
//        // then
//        assertThat(result).isTrue();
   }

    @Test
    void thatLocationParameterIsBlank() { // todo isLocationParameterBlank_whenPassedParamContainsWhitespaces_returnsTrue
        // when
        // String str = " ";
        // then
        //   Assertions.assert(databaseInputValidator.isLocationParameterBlank(str));

        // given
        String arg = " ";

        // when
        boolean result = databaseInputValidator.isLocationParameterBlank(arg);

        // then
        assertThat(result).isTrue();

        // todo adjust other tests
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
        Assertions.assertTrue(databaseInputValidator.isNotLatitudeMatchesRegexPattern(str));
    }

    @Test
    void thatCoordinateDoesNotMatchLettersAndNumbersRegexPattern() {
        // when
        String str = "342dsdf";
        // then
        Assertions.assertTrue(databaseInputValidator.isNotLongitudeMatchesRegexPattern(str));
    }

    @Test
    void thatCoordinateDoesNotMatchNumberWithThreeDigitsBeforeDotRegexPattern() {
        // when
        String str = "346.234";
        // then
        Assertions.assertTrue(databaseInputValidator.isNotLongitudeMatchesRegexPattern(str));
    }


    @Test
    void thatCoordinateDoesNotMatchNumberWithThreeDigitsRegexPattern() {
        // when
        String str = "911";
        // then
        Assertions.assertTrue(databaseInputValidator.isNotLongitudeMatchesRegexPattern(str));
    }

    @Test
    void thatCoordinateDoesNotMatchNumberWithoutPrefixPartRegexPattern() {
        // when
        String str = ".89";
        // then
        Assertions.assertTrue(databaseInputValidator.isNotLongitudeMatchesRegexPattern(str));
    }
}
