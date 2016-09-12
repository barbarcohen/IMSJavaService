package com.honeywell.ims.api.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by h134602 on 9/12/2016.
 */
public class MainData {

	private double temp;

	@JsonProperty(value = "temp_min")
	private double tempMin;

	public double getTempMax() {
		return tempMax;
	}

	public void setTempMax(final double tempMax) {
		this.tempMax = tempMax;
	}

	@JsonProperty(value = "temp_max")
	private double tempMax;

	private int humidity;

	private double pressure;

	public double getTemp() {

		return temp;
	}

	public void setTemp(final double temp) {
		this.temp = temp;
	}

	public double getTempMin() {
		return tempMin;
	}

	public void setTempMin(final double tempMin) {
		this.tempMin = tempMin;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(final int humidity) {
		this.humidity = humidity;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(final double pressure) {
		this.pressure = pressure;
	}
}
