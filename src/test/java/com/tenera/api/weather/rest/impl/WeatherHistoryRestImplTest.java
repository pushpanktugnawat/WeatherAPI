package com.tenera.api.weather.rest.impl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.tenera.api.weather.model.WeatherHistory;
import com.tenera.api.weather.model.WeatherHistoryWrapper;
import com.tenera.api.weather.service.IWeatherHistoryService;
import com.tenera.api.weather.utils.WeatherApiConstants;

/**
 * The Class WeatherHistoryRestImplTest.
 */
@WebMvcTest(controllers = WeatherHistoryRestImpl.class)
@ActiveProfiles("test")
public class WeatherHistoryRestImplTest {

	/** The weather history service. */
	@MockBean
	private IWeatherHistoryService weatherHistoryService;
	
	@Autowired
    private MockMvc mockMvc;
	
	private ResponseEntity<WeatherHistoryWrapper> responseEntity;
	
	/**
	 * Sets the up.
	 */
	@BeforeEach                        
	void setUp() {                               

		List<WeatherHistory> histories = new ArrayList<WeatherHistory>();
		
		WeatherHistory firstHistory = new WeatherHistory();
		firstHistory.setPressure(50);
		firstHistory.setTemp(37);
		firstHistory.setUmbrella(true);
		histories.add(firstHistory);
		
		WeatherHistory secondHistory = new WeatherHistory();
		secondHistory.setPressure(20);
		secondHistory.setTemp(30);
		secondHistory.setUmbrella(true);
		histories.add(secondHistory);
		
		WeatherHistoryWrapper historyWrapper= new WeatherHistoryWrapper();
		historyWrapper.setAvg_pressure(35);
		historyWrapper.setAvg_temp(33.5);
		historyWrapper.setHistory(histories);
		
		this.responseEntity= new ResponseEntity<WeatherHistoryWrapper>(historyWrapper,HttpStatus.OK);
	}
	
	@Test
	 @Tag("getHistoryWeatherInformation")
	 void getHistoryWeatherInformation_Success() throws Exception {
		   
		   Mockito.when(weatherHistoryService.findWeatherHistoryByCityName(WeatherApiConstants.LOCATION_NAME)).thenReturn(responseEntity);
	    	mockMvc.perform(get("/history?location="+WeatherApiConstants.LOCATION_NAME)
	                .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	    }
	
}
