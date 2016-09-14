package com.honeywell.ims.api.web;

import java.util.Date;

import com.honeywell.ims.enums.WateringStatus;

/**
 * Created by h134602 on 9/12/2016.
 */
public class DeviceData {

	private WateringStatus status;

	private double humidity;

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(final double humidity) {
		this.humidity = humidity;
	}

	public WateringStatus getStatus() {
		return status;
	}

	public void setStatus(final WateringStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DeviceData{" +
			"status=" + status +
			", humidity=" + humidity +
			'}';
	}
}
