package com.tenera.api.weather.rest.impl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.tenera.api.weather.service.IWeatherInfoService;
import com.tenera.api.weather.utils.WeatherApiConstants;

/**
 * The Class WeatherInfoRestImplTest.
 */
@WebMvcTest(controllers = WeatherInfoRestImpl.class)
@ActiveProfiles("test")
public class WeatherInfoRestImplTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private IWeatherInfoService iWeatherInfoService;
	
	private ResponseEntity<String> responeEntity;
	
	/**
	 * Sets the up.
	 */
	@BeforeEach                        
	void setUp() {                               

		String weatherObj="{\"temp\":8.34,\"umbrella\":false,\"pressure\":1003}";
		responeEntity= new ResponseEntity<String>(weatherObj,HttpStatus.OK);
	}
	
	 /**
 	 * Gets the current weather information success.
 	 *
 	 * @return the current weather information success
 	 * @throws Exception the exception
 	 */
 	@Test
	 @Tag("getCurrentWeatherInformation")
	 void getCurrentWeatherInformation_Success() throws Exception {
		   
		   Mockito.when(iWeatherInfoService.getCurrentWeatherInformation(WeatherApiConstants.LOCATION_NAME)).thenReturn(responeEntity);
	    	mockMvc.perform(get("/current?location="+WeatherApiConstants.LOCATION_NAME)
	                .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	    }
}
