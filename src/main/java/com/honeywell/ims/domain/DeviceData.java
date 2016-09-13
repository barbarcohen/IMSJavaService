package com.honeywell.ims.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by h134602 on 9/13/2016.
 */
public class DeviceData {

	@JsonProperty
	private String deviceId;

	@JsonProperty("Hum")
	private int humidity;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(final String deviceId) {
		this.deviceId = deviceId;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(final int humidity) {
		this.humidity = humidity;
	}
}
