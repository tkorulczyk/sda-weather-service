# SDA Weather Service
WeatherService is a Java application designed to provide the current weather forecast for a specified city. It efficiently fetches weather data from external APIs and caches the results in a in memory H2 database, enhancing performance and minimizing redundant API calls.

![AI Generated Lighting](src/project_desc/img/ai-generated-7922513_1280.jpg?raw=true "AI Generated Lighting")
**Disclaimer:** The image was generated using artificial intelligence and, as such, is not subject to copyright protection.

## Key Features:

Weather Data Retrieval: Utilizing the Weather Stack API, users can acquire weather information based on their specific inputs, like location, and the desired date for weather data (either current forecast or historical data).

Local Caching: For improved performance and reduced API dependency, fetched weather details can be saved to an in-memory H2 database.

ChatGPT Integration: In its latest update, WeatherService has integrated ChatGPT to offer recommendations on suitable attire and potential outdoor activities based on the acquired weather information.

## Background:
This project was crafted as part of the Java Fundamentals course provided by the Software Development Academy. It has been structured adhering to the clean architecture principles to ensure maintainability and scalability.

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
api.openai.key=YOUR_OPENAI_API_KEY
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
