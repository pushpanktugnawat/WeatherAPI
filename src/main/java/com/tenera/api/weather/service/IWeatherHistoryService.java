/**
 * 
 */
package com.tenera.api.weather.service;

import org.springframework.http.ResponseEntity;

import com.tenera.api.weather.model.WeatherHistoryWrapper;

/**
 * The Interface IWeatherHistoryService.
 */
public interface IWeatherHistoryService {

	/**
	 * Creates the weather history.
	 *
	 * @param temp the temp
	 * @param pressure the pressure
	 * @param umbrellaRequiredOrNot the umbrella required or not
	 * @param location the location
	 * @return 
	 */
	String createWeatherHistory(double temp, double pressure, boolean umbrellaRequiredOrNot, String location);

	/**
	 * Find weather history by city name.
	 *
	 * @param cityName the city name
	 * @return the weather history wrapper
	 * @throws Exception 
	 */
	ResponseEntity<WeatherHistoryWrapper> findWeatherHistoryByCityName(String cityName) throws Exception;

	
}
