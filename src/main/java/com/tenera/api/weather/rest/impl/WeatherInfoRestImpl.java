package com.tenera.api.weather.rest.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tenera.api.weather.service.IWeatherInfoService;

/**
 * The Class WeatherInfoRestImpl.
 */
@RestController
public class WeatherInfoRestImpl {

	private Logger logger=LoggerFactory.getLogger(WeatherInfoRestImpl.class);
	
	/** The weather history service. */
	@Autowired
	private IWeatherInfoService weatherInfoService;
	
	/**
	 * Gets the current weather information.
	 *
	 * @param location the location
	 * @return the current weather information
	 */
	@GetMapping("/current")
	private ResponseEntity<String> getCurrentWeatherInformation(@RequestParam(name = "location",required = true)String location)
	{
		logger.info("@method getCurrentWeatherInformation @param location "+location);
		return weatherInfoService.getCurrentWeatherInformation(location);
		
	}
	
	
}
