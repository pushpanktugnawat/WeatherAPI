package com.tenera.api.weather.model;

import java.util.List;

/**
 * The Class WeatherHistoryWrapper.
 */
public class WeatherHistoryWrapper {

	/** The avg temp. */
	private double avg_temp;
	
	/** The avg pressure. */
	private double avg_pressure;
	
	/** The history. */
	private List<WeatherHistory> history;

	/**
	 * @return the avg_temp
	 */
	public double getAvg_temp() {
		return avg_temp;
	}

	/**
	 * @param avg_temp the avg_temp to set
	 */
	public void setAvg_temp(double avg_temp) {
		this.avg_temp = avg_temp;
	}

	/**
	 * @return the avg_pressure
	 */
	public double getAvg_pressure() {
		return avg_pressure;
	}

	/**
	 * @param avg_pressure the avg_pressure to set
	 */
	public void setAvg_pressure(double avg_pressure) {
		this.avg_pressure = avg_pressure;
	}

	/**
	 * @return the history
	 */
	public List<WeatherHistory> getHistory() {
		return history;
	}

	/**
	 * @param history the history to set
	 */
	public void setHistory(List<WeatherHistory> history) {
		this.history = history;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(avg_pressure);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(avg_temp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((history == null) ? 0 : history.hashCode());
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
		WeatherHistoryWrapper other = (WeatherHistoryWrapper) obj;
		if (Double.doubleToLongBits(avg_pressure) != Double.doubleToLongBits(other.avg_pressure))
			return false;
		if (Double.doubleToLongBits(avg_temp) != Double.doubleToLongBits(other.avg_temp))
			return false;
		if (history == null) {
			if (other.history != null)
				return false;
		} else if (!history.equals(other.history))
			return false;
		return true;
	}
	
	
	
	
}
