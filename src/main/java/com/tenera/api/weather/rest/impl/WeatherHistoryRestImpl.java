package com.tenera.api.weather.rest.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tenera.api.weather.model.WeatherHistoryWrapper;
import com.tenera.api.weather.service.IWeatherHistoryService;

/**
 * The Class WeatherHistoryRestImpl.
 */
@RestController
public class WeatherHistoryRestImpl {

	private Logger logger=LoggerFactory.getLogger(WeatherHistoryRestImpl.class);
	
	/** The weather history service. */
	@Autowired
	private IWeatherHistoryService weatherHistoryService;
	
	@GetMapping("/history")
	private ResponseEntity<WeatherHistoryWrapper> getHistoryWeatherInformation(@RequestParam(name = "location",required = true)String location) throws Exception
	{
		logger.info("@method getHistoryWeatherInformation @param location "+location);
		return weatherHistoryService.findWeatherHistoryByCityName(location);
		
	}
}
