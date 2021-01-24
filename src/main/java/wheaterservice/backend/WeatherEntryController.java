package wheaterservice.backend;

import java.util.List;

// todo it's not a weather, it is a location
public class WeatherEntryController {

    private final WeatherEntryService weatherEntryService;

    public WeatherEntryController(WeatherEntryService weatherEntryService) {
        this.weatherEntryService = weatherEntryService;
    }

    public String createNewWeatherLocation(String countryName, String regionName, String locationName, String latitude) {
        WeatherEntry newWeatherEntry = weatherEntryService.createNewWeatherEntry(countryName, regionName, locationName, latitude);
        return newWeatherEntry.toString();
    }

    public String readWeatherLocation() {
        List<WeatherEntry> weatherLocations = weatherEntryService.readWeatherLocations();
        return weatherLocations.toString();
    }
}
