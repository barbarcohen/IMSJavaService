package com.honeywell.ims.api.device;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by h134602 on 9/13/2016.
 */
public class SensorDataRows {

	@JsonProperty(value = "Hum")
	private double humidity;

	@JsonProperty(value = "IsRunning")
	private boolean isRunning;

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(final double humidity) {
		this.humidity = humidity;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setIsRunning(final boolean isRunning) {
		this.isRunning = isRunning;
	}
}
