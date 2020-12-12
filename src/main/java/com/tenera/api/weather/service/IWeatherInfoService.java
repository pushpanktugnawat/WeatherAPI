package com.tenera.api.weather.service;

import org.springframework.http.ResponseEntity;

public interface IWeatherInfoService {

	/**
	 * Gets the current weather information.
	 *
	 * @param location the location
	 * @return the current weather information
	 */
	ResponseEntity<String> getCurrentWeatherInformation(String location);

}
