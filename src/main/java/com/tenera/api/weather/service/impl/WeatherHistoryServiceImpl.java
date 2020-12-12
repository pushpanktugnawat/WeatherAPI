package com.tenera.api.weather.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tenera.api.weather.dao.IWeatherHistoryDao;
import com.tenera.api.weather.model.WeatherHistory;
import com.tenera.api.weather.model.WeatherHistoryWrapper;
import com.tenera.api.weather.service.IWeatherHistoryService;
import com.tenera.api.weather.utils.WeatherApiConstants;

/**
 * The Class WeatherHistoryServiceImpl.
 */
@Service
public class WeatherHistoryServiceImpl implements IWeatherHistoryService{

	/** The logger. */
	private Logger logger=LoggerFactory.getLogger(WeatherHistoryServiceImpl.class);
	
	@Autowired
	private IWeatherHistoryDao weatherHistoryDao;
	
	
	
	/**
	 * Find weather history by city name.
	 *
	 * @param cityName the city name
	 * @return the weather history wrapper
	 * @throws Exception 
	 */
	@Override
	public ResponseEntity<WeatherHistoryWrapper> findWeatherHistoryByCityName(String cityName) throws Exception
	{
		
		List<WeatherHistory> weatherHistories=weatherHistoryDao.findTop5ByCityNameContainsOrderByIdDesc(cityName);
		
		if(!CollectionUtils.isEmpty(weatherHistories)) 
		{
			WeatherHistoryWrapper weatherHistoryWrapper=new WeatherHistoryWrapper();
			
			double avgTemp= weatherHistories.stream().mapToDouble(WeatherHistory::getTemp).average().orElse(Double.NaN);
			double avgPressure=weatherHistories.stream().mapToDouble(WeatherHistory::getPressure).average().orElse(Double.NaN);
			logger.info("@method findWeatherHistoryByCityName @param avgTemp "+avgTemp+" avgPressure "+avgPressure+" "
					+ "@weatherHistories "+weatherHistories.size());
			
			weatherHistoryWrapper.setHistory(weatherHistories);
			weatherHistoryWrapper.setAvg_pressure(Double.parseDouble(String.format("%.2f", avgPressure)));
			weatherHistoryWrapper.setAvg_temp(Double.parseDouble(String.format("%.2f",avgTemp)));
			return new ResponseEntity<>(weatherHistoryWrapper,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new WeatherHistoryWrapper(),HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * Creates the weather history.
	 *
	 * @param temp the temp
	 * @param pressure the pressure
	 * @param umbrellaRequiredOrNot the umbrella required or not
	 */
	@Override
	public String createWeatherHistory(double temp, double pressure, boolean umbrellaRequiredOrNot,String cityName) {
		
		logger.info("@method createWeatherHistory @cityname "+cityName);
		WeatherHistory weatherHistory=new WeatherHistory();
		weatherHistory.setCityName(cityName);
		weatherHistory.setPressure(pressure);
		weatherHistory.setTemp(temp);
		weatherHistory.setUmbrella(umbrellaRequiredOrNot);
		weatherHistoryDao.save(weatherHistory);
		return WeatherApiConstants.SUCESS;
	}
	
}
