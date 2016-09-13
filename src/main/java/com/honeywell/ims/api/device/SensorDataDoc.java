package com.honeywell.ims.api.device;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by h134602 on 9/13/2016.
 */
public class SensorDataDoc {

	@JsonProperty(value = "Hum")
	private String humidityOriginal;

	@JsonProperty(value = "IsRunning")
	private boolean isRunning;

	public String getHumidityOriginal() {
		return humidityOriginal;
	}

	public void setHumidityOriginal(final String humidityOriginal) {
		this.humidityOriginal = humidityOriginal;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setIsRunning(final boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public String toString() {
		return "SensorDataDoc{" +
			"humidityOriginal='" + humidityOriginal + '\'' +
			", isRunning=" + isRunning +
			'}';
	}
}
