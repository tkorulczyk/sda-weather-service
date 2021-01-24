package wheaterservice.backend;

public class WeatherEntryController {
    private final WeatherEntryService weatherEntryService;

    public WeatherEntryController(WeatherEntryService weatherEntryService) {
        this.weatherEntryService = weatherEntryService;
    }


    public String createNewWeatherLocation(String countryName, String regionName, String locationName, String latitude) {

        return null;
    }

    public String readWeatherLocation() {
        return null;
    }
}
