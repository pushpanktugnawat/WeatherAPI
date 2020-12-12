/**
 * 
 */
package com.tenera.api.weather.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tenera.api.weather.model.WeatherHistory;

/**
 * The Interface WeatherHistoryDao.
 */
public interface IWeatherHistoryDao extends CrudRepository<WeatherHistory,Integer>{

	/**
	 * Find top 5 by cityname order by id desc.
	 *
	 * @param cityName the city name
	 * @return the list
	 */
	List<WeatherHistory> findTop5ByCityNameContainsOrderByIdDesc(String cityName);
}
