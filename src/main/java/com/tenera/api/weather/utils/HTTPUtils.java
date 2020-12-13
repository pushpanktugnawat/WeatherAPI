package com.tenera.api.weather.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The Class HTTPUtils.
 */

public class HTTPUtils {

	private Logger logger=LoggerFactory.getLogger(HTTPUtils.class);
	/**
	 * Send http request for weather information.
	 *
	 * @param location the location
	 * @return the string
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	public String sendHttpRequestForWeatherInformation(String location) throws ClientProtocolException, IOException, ParseException, JSONException
	{
		try (final CloseableHttpClient httpclient = HttpClients.createDefault()) 
		{
			final HttpGet httpget = new HttpGet(WeatherApiConstants.BASE_URL+location);

			logger.info("@method sendHttpRequestForWeatherInformation Executing request " + httpget.getMethod() + " " + httpget.getURI());

			final CloseableHttpResponse responseBody = httpclient.execute(httpget);

			return convertResponseToString(responseBody);
		}
	}

	/**
	 * Convert response to string.
	 *
	 * @param responseBody the response body
	 * @return the string
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws JSONException 
	 */
	private String convertResponseToString(CloseableHttpResponse response) throws ParseException, IOException, JSONException 
	{

		final int status = response.getStatusLine().getStatusCode(); 

		logger.info("@method convertResponseToString @param status " + status);

		final HttpEntity entity = response.getEntity();

		return entity != null ? EntityUtils.toString(entity) : null; 

	}
}


