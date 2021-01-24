package wheaterservice.backend;

import java.util.List;

public class WeatherEntryService {

    private WeatherEntryRepository weatherEntryRepository;

    public WeatherEntryService(WeatherEntryRepository weatherEntryRepository) {
        this.weatherEntryRepository = weatherEntryRepository;
    }

    public WeatherEntry createNewWeatherEntry(String countryName, String regionName, String locationName, String latitude) {
        return null;
    }

    public List<WeatherEntry> readWeatherLocations() {
        return null;
    }
}
