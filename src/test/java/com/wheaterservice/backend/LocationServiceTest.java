package com.wheaterservice.backend;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocationServiceTest {
private static LocationService locationService;

    @BeforeEach
    void setUp() {
        LocationRepository locationRepository = new LocationRepositoryMock();
        locationService = new LocationService(locationRepository);
    }

    @Test
    void createNewLocation_whenCityHasANumber_throwsException() {
        //when
        Throwable throwable = Assertions.catchThrowable(() -> locationService.createNewLocation("Poland","Lub","4654","34.54","32,43"));

        // then
        org.assertj.core.api.Assertions.assertThat(throwable).isInstanceOf(RuntimeException.class);
    }
}