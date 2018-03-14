package org.sonatype.mavenbook.weather;

import java.io.InputStream;

public class WeatherService {

	public WeatherService() {		
	}

	public String retrieveForecast (String zip) throws Exception {
		// Retrieve data
		InputStream dataIn = new YahooRetriever().retrieve(zip);

		// Parse data
		Weather weather = new YahooParser().parse(dataIn);

		// Format (Print) Data
		return new WeatherFormatter().format(weather);
	}

}