package com.tenera.api.weather.service.impl;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.tenera.api.weather.service.IWeatherHistoryService;
import com.tenera.api.weather.service.IWeatherInfoService;
import com.tenera.api.weather.utils.HTTPUtils;
import com.tenera.api.weather.utils.WeatherApiConstants;

/**
 * The Class WeatherInfoServiceImpl.
 */
@Controller
public class WeatherInfoServiceImpl implements IWeatherInfoService{

	
	/** The logger. */
	private Logger logger=LoggerFactory.getLogger(WeatherInfoServiceImpl.class);
	
	/** The weather history service. */
	@Autowired
	private IWeatherHistoryService weatherHistoryService;
	

	
	/**
	 * Gets the current weather information.
	 *
	 * @param location the location
	 * @return the current weather information
	 */
	@Override
	public ResponseEntity<String> getCurrentWeatherInformation(String location) 
	{
		logger.info("@method getCurrentWeatherInformation @param location "+location);
		
		try 
		{
			HTTPUtils httpUtils= new HTTPUtils();
			String responseBody=httpUtils.sendHttpRequestForWeatherInformation(location);
			
			if(responseBody!=null && !responseBody.isEmpty())
			{
				JSONObject jsonObject=new JSONObject(responseBody);
				/**
				 * Weather Info is return irregular value of status Code
				 * */
				int responseCode=jsonObject.getInt(WeatherApiConstants.RESPONSE_CODE);
				
				if(responseCode==HttpStatus.OK.value())
				{
					JSONObject responseObject=new JSONObject();
					
					JSONObject mainJsonObjectForTempNPressure=jsonObject.getJSONObject(WeatherApiConstants.MAIN_KEY);
					
					double temp=0.0,pressure=0.0;
					if(mainJsonObjectForTempNPressure!=null)
					{
						temp=  mainJsonObjectForTempNPressure.getDouble(WeatherApiConstants.TEMP);
						pressure= mainJsonObjectForTempNPressure.getDouble(WeatherApiConstants.PRESSURE);
						
						responseObject.put(WeatherApiConstants.TEMP,temp);
						responseObject.put(WeatherApiConstants.PRESSURE, pressure);
					}
					
					JSONObject sysJsonObjectForCountryCode=jsonObject.getJSONObject(WeatherApiConstants.SYS);
					
					if(sysJsonObjectForCountryCode!=null)
					{
						String countryCode=sysJsonObjectForCountryCode.getString(WeatherApiConstants.COUNTRY);
						location=location.concat(","+countryCode);
					}
					
					boolean umbrellaRequiredOrNot=getUmbrellaRequiredOrNot(jsonObject);
					responseObject.put(WeatherApiConstants.UMBRELLA, umbrellaRequiredOrNot);
					
					weatherHistoryService.createWeatherHistory(temp,pressure,umbrellaRequiredOrNot,location);
					
					return new ResponseEntity<String>(responseObject.toString(),HttpStatus.OK);
				}else 
				{
					return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
				}
			}
		} catch (ClientProtocolException e) 
		{
			logger.error("error occurred @exception ClientProtocolException @method getCurrentWeatherInformation ");
			
		} catch (IOException e) 
		{
			logger.error("error occurred @exception IOException @method getCurrentWeatherInformation");
		} catch (JSONException e) 
		{
			logger.error("error occurred @exception JSONException @method getCurrentWeatherInformation");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	
	/**
	 * Gets the umbrella required or not.
	 *
	 * @param weatherCondition the weather condition
	 * @return the umbrella required or not
	 * @throws JSONException 
	 */
	private boolean getUmbrellaRequiredOrNot(JSONObject jsonObject) throws JSONException 
	{
		logger.info("@method getUmbrellaRequiredOrNot");
		
		String weatherCondition="";
		if(jsonObject.getJSONArray(WeatherApiConstants.WEATHER)!=null && 
				jsonObject.getJSONArray(WeatherApiConstants.WEATHER).length()>0) 
		{
			weatherCondition=(String) jsonObject.getJSONArray(WeatherApiConstants.WEATHER).
					getJSONObject(0).get(WeatherApiConstants.MAIN_KEY);
		}
		
		if(weatherCondition.equals(WeatherApiConstants.DRIZZLE) || weatherCondition.equals(WeatherApiConstants.RAIN) 
				|| weatherCondition.equals(WeatherApiConstants.THUNDERSTORM))
		{
			return true;
		}
		return false;
	}
}
