# SDA Weather Service
WeatherService is an application that allows you to get the current weather forecast for a selected city. It uses external APIs to fetch weather data and stores it locally in a database to improve performance and reduce the number of API calls.


## Features
- Searching for the weather forecast for a selected city
- Storing weather forecasts in a database
- The user can display the latest forecasts for a given city stored in the database

## Installation
### Requirements
- Java 11+
- Maven
- H2 Database

### Instructions
Clone the repository:
```bash
git clone https://github.com/tkorulczyk/sda-weatherservice.git
```

## Navigate to the directory with the project:

bash
Copy code
cd weatherservice

Build and run the project:



bash
Copy code

```bash
mvn clean install
mvn spring-boot:run
```

## Usage
After launching the application, you can get the weather forecast for any city by typing the city name into the user interface.

## API
The application uses external APIs to fetch weather forecasts. API keys for these services are required, which can be obtained on the service providers' websites:

OpenWeather: registration link
WeatherStack: registration link
API keys should be placed in the application.properties file:

vbnet
Copy code
```bash
api.openweather.key=YOUR_OPENWEATHER_API_KEY
api.weatherstack.key=YOUR_WEATHERSTACK_API_KEY
```

## Data model
The application stores weather forecasts in an H2 database. A weather forecast consists of the following elements:

ID: Unique identifier of the forecast
Date: The date when the forecast was downloaded
City: The city for which the forecast was downloaded
Temperature: Current temperature
Wind speed: Current wind speed
Wind direction: Current wind direction
Pressure: Current atmospheric pressure
Humidity: Current air humidity
Weather description: A short description of the current weather

## Tests
The application includes a set of unit and integration tests. They can be run using the Maven command:
### bash
Copy code
```bash
mvn test
```

## Contribution
If you want to contribute to the development of this project, clone the repository, create a new branch, add your changes, and submit a Pull Request. All ideas and suggestions are welcome!

## License
This project is released under the MIT license. Detailed information can be found in the LICENSE file.
