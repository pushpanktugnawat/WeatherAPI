/**
 * 
 */
package com.tenera.api.weather.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Class WeatherHistory.
 *
 * @author Pushpank
 */

@Table(name="weather_history")
@Entity
public class WeatherHistory implements Serializable{

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private int id;
	
	/** The city name. */
	@Column(name="city_name")
	@JsonIgnore
	private String cityName;
	
	/** The temp. */
	@Column(name="temp")
	private double temp;
	
	/** The pressure. */
	@Column(name="pressure")
	private double pressure;

	/** The umbrella. */
	@Column(name="umbrella")
	private boolean umbrella;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the temp
	 */
	public double getTemp() {
		return temp;
	}

	/**
	 * @param temp the temp to set
	 */
	public void setTemp(double temp) {
		this.temp = temp;
	}

	/**
	 * @return the pressure
	 */
	public double getPressure() {
		return pressure;
	}

	/**
	 * @param pressure the pressure to set
	 */
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	/**
	 * @return the umbrella
	 */
	public boolean isUmbrella() {
		return umbrella;
	}

	/**
	 * @param umbrella the umbrella to set
	 */
	public void setUmbrella(boolean umbrella) {
		this.umbrella = umbrella;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(pressure);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.temp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (umbrella ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeatherHistory other = (WeatherHistory) obj;
		if (cityName == null) {
			if (other.cityName != null)
				return false;
		} else if (!cityName.equals(other.cityName))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(pressure) != Double.doubleToLongBits(other.pressure))
			return false;
		if (Double.doubleToLongBits(temp) != Double.doubleToLongBits(other.temp))
			return false;
		if (umbrella != other.umbrella)
			return false;
		return true;
	}

	
}
