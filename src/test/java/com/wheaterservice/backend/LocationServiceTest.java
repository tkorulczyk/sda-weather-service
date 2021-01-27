package com.wheaterservice.backend;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LocationServiceTest {

    private static LocationService locationService;

    @BeforeAll
    static void setUp() {
        LocationRepository locationRepository = new LocationRepositoryMock();
        locationService = new LocationService(locationRepository);
    }

    @Test
    void createNewLocation_createsLocation() {
        // when
        Location result = locationService.createNewLocation("Poland", "Pomorze", "Gdansk", "40.5", "50.1");

        // then
        assertThat(result).isNotNull();
        assertThat(result.getCityName()).isEqualTo("Gdansk");
        assertThat(result.getRegionName()).isEqualTo("Pomorze");
        assertThat(result.getCountryName()).isEqualTo("Poland");
        assertThat(result.getLatitude()).isEqualTo(40.5f);  // todo there is 0.0 - why?
        assertThat(result.getLongitude()).isEqualTo(50.1f); // todo there is 0.0 - why?
    }

    @Test
    void createNewLocation_whenRegionIsBlank_createsLocation() {
        // when
        Location result = locationService.createNewLocation("Poland", "", "Gdansk", "40.5", "50.1");

        // then
        assertThat(result).isNotNull();
        assertThat(result.getCityName()).isEqualTo("Gdansk");
        assertThat(result.getRegionName()).isNull();        // todo region may be empty, check the requirements
        assertThat(result.getCountryName()).isEqualTo("Poland");
        assertThat(result.getLatitude()).isEqualTo(40.5f);  // todo there is 0.0 - why?
        assertThat(result.getLongitude()).isEqualTo(50.1f); // todo there is 0.0 - why?
    }

    @Test
    void createNewLocation_whenCityHasANumber_throwsException() {
        //when
        Throwable throwable = Assertions.catchThrowable(() -> locationService.createNewLocation("Poland", "Lub", "4654", "34.54", "32,43"));

        // then
        assertThat(throwable).isInstanceOf(RuntimeException.class); // todo this test doesn't pass, it means a validation doesn't work properly
    }
}
