package com.honeywell.ims.api.web;

import java.util.Date;

/**
 * Created by h134602 on 9/13/2016.
 */
public class Settings {

	private String deviceId;

	private Date nextWatering;

	private long wateringDuration;

	private int minHumidityThreshold;

	private boolean forceIrrigation;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(final String deviceId) {
		this.deviceId = deviceId;
	}

	public Date getNextWatering() {
		return nextWatering;
	}

	public void setNextWatering(final Date nextWatering) {
		this.nextWatering = nextWatering;
	}

	public long getWateringDuration() {
		return wateringDuration;
	}

	public void setWateringDuration(final long wateringDuration) {
		this.wateringDuration = wateringDuration;
	}

	public int getMinHumidityThreshold() {
		return minHumidityThreshold;
	}

	public void setMinHumidityThreshold(final int minHumidityThreshold) {
		this.minHumidityThreshold = minHumidityThreshold;
	}

	public boolean isForceIrrigation() {
		return forceIrrigation;
	}

	public void setForceIrrigation(final boolean forceIrrigation) {
		this.forceIrrigation = forceIrrigation;
	}
}
