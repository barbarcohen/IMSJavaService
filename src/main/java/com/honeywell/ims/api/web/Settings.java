package com.honeywell.ims.api.web;

import java.util.Date;

import com.honeywell.ims.service.Utils;

/**
 * Created by h134602 on 9/13/2016.
 */
public class Settings {

	private String deviceId;

	//date
	private Date nextWatering;

	//seconds
	private int delay;

	//seconds
	private long wateringDuration;

	private int minHumidityThreshold;

	private boolean forceIrrigation;

	private int forecastHours;

	public static Settings createDefault() {
		Settings settings = new Settings();
		settings.setNextWatering(Utils.getCurrentDateTime().plusHours(1).toDate());
		settings.setDelay(1);
		settings.setWateringDuration(5);
		settings.setMinHumidityThreshold(10);
		settings.setForecastHours(24);
		return settings;
	}

	public int getForecastHours() {
		return forecastHours;
	}

	public void setForecastHours(final int forecastHours) {
		this.forecastHours = forecastHours;
	}

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

	public int getDelay() {
		return delay;
	}

	public void setDelay(final int delay) {
		this.delay = delay;
	}

	@Override
	public String toString() {
		return "Settings{" +
			"deviceId='" + deviceId + '\'' +
			", nextWatering=" + nextWatering +
			", delay=" + delay +
			", wateringDuration=" + wateringDuration +
			", minHumidityThreshold=" + minHumidityThreshold +
			", forceIrrigation=" + forceIrrigation +
			", forecastHours=" + forecastHours +
			'}';
	}
}
