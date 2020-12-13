package com.tenera.api.weather.service.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.tenera.api.weather.model.WeatherHistory;
import com.tenera.api.weather.utils.HTTPUtils;
import com.tenera.api.weather.utils.WeatherApiConstants;

@ExtendWith(MockitoExtension.class)
@Transactional
public class WeatherInfoServiceImplTest {

	
	@InjectMocks
	private WeatherInfoServiceImpl weatherInfoServiceImpl;
	
	@Mock
	private WeatherHistoryServiceImpl weatherHistoryService;
	 
	 
	@InjectMocks
	HTTPUtils httpUtils;
	
	private ResponseEntity<String> responseEntity;
	
	private WeatherHistory weatherHistory;
	
	/**
	 * Sets the up.
	 * @throws JSONException 
	 */
	@BeforeEach                        
	void setUp() throws JSONException {                               
		
		
		this.weatherHistory= new WeatherHistory();
		this.weatherHistory.setPressure(50);
		this.weatherHistory.setTemp(37);
		this.weatherHistory.setUmbrella(true);
		
		JSONObject responseObject=new JSONObject();
		responseObject.put(WeatherApiConstants.TEMP,7.8);
		responseObject.put(WeatherApiConstants.PRESSURE, 1006);
		responseObject.put(WeatherApiConstants.UMBRELLA, false);
		
		this.responseEntity= new ResponseEntity<String>(responseObject.toString(),HttpStatus.OK);
		
	}
	
	/**
	 * Gets the current weather information sucess.
	 *
	 * @return the current weather information sucess
	 * @throws Exception the exception
	 */
	@Test
	public void getCurrentWeatherInformation_Sucess() throws Exception {

		ResponseEntity<String> expected = weatherInfoServiceImpl.getCurrentWeatherInformation(WeatherApiConstants.LOCATION_NAME);

		Assertions.assertEquals(expected.getStatusCodeValue(), this.responseEntity.getStatusCodeValue());
	}
	
}
