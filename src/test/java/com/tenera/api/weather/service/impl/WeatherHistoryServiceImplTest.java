package com.tenera.api.weather.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tenera.api.weather.dao.IWeatherHistoryDao;
import com.tenera.api.weather.model.WeatherHistory;
import com.tenera.api.weather.model.WeatherHistoryWrapper;
import com.tenera.api.weather.utils.WeatherApiConstants;

@ExtendWith(MockitoExtension.class)
public class WeatherHistoryServiceImplTest {

	@Mock
	private IWeatherHistoryDao weatherHistoryDao;
	
	@InjectMocks
	private WeatherHistoryServiceImpl weatherHistoryService;
	
	private List<WeatherHistory> weatherHistories;
	
	private ResponseEntity<WeatherHistoryWrapper> responseEntity;
	
	/**
	 * Sets the up.
	 */
	@BeforeEach                        
	void setUp() {                               

		this.weatherHistories = new ArrayList<WeatherHistory>();
		
		WeatherHistory firstHistory = new WeatherHistory();
		firstHistory.setPressure(50);
		firstHistory.setTemp(37);
		firstHistory.setUmbrella(true);
		weatherHistories.add(firstHistory);
		
		WeatherHistory secondHistory = new WeatherHistory();
		secondHistory.setPressure(20);
		secondHistory.setTemp(30);
		secondHistory.setUmbrella(true);
		weatherHistories.add(secondHistory);
		
		WeatherHistoryWrapper historyWrapper= new WeatherHistoryWrapper();
		historyWrapper.setAvg_pressure(35);
		historyWrapper.setAvg_temp(33.5);
		historyWrapper.setHistory(weatherHistories);
		
		this.responseEntity= new ResponseEntity<WeatherHistoryWrapper>(historyWrapper,HttpStatus.OK);
		
	}
	
	/**
	 * Find weather history by city name sucess.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void findWeatherHistoryByCityName_Sucess() throws Exception {

		Mockito.when(weatherHistoryDao.findTop5ByCityNameContainsOrderByIdDesc(WeatherApiConstants.LOCATION_NAME)).thenReturn(this.weatherHistories);
		
		ResponseEntity<WeatherHistoryWrapper> expected = weatherHistoryService.findWeatherHistoryByCityName(WeatherApiConstants.LOCATION_NAME);

		Assertions.assertEquals(expected, this.responseEntity);
	}
	
	/**
	 * Find weather history by city name no data.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void findWeatherHistoryByCityName_NoData() throws Exception {

		Mockito.when(weatherHistoryDao.findTop5ByCityNameContainsOrderByIdDesc(WeatherApiConstants.LOCATION_NAME)).thenReturn(null);
		
		ResponseEntity<WeatherHistoryWrapper> expected = weatherHistoryService.findWeatherHistoryByCityName(WeatherApiConstants.LOCATION_NAME);

		Assertions.assertEquals(expected, new ResponseEntity<>(new WeatherHistoryWrapper(),HttpStatus.NO_CONTENT));
	}
	
}
