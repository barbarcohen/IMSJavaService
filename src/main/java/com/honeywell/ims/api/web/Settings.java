package com.honeywell.ims.api.web;

import java.util.Date;

import com.honeywell.ims.service.Utils;

/**
 * Created by h134602 on 9/13/2016.
 */
public class Settings {

	private String deviceId;

	//date
	private Date nextWateringDate;
	private int nextWaterigSeconds;
	//seconds
	private int delay;

	//seconds
	private long wateringDuration;

	private int minHumidityThreshold;

	private boolean forceIrrigation;

	private int forecastHours;

	public static Settings createDefault() {
		Settings settings = new Settings();
		settings.setNextWateringDate(Utils.getCurrentDateTime().plusMinutes(1).toDate());
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

	public Date getNextWateringDate() {
		return nextWateringDate;
	}

	public void setNextWateringDate(final Date nextWateringDate) {
		this.nextWateringDate = nextWateringDate;
	}

	public int getNextWaterigSeconds() {
		return nextWaterigSeconds;
	}

	public void setNextWaterigSeconds(final int nextWaterigSeconds) {
		this.nextWaterigSeconds = nextWaterigSeconds;
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
			", nextWatering=" + nextWateringDate +
			", delay=" + delay +
			", wateringDuration=" + wateringDuration +
			", minHumidityThreshold=" + minHumidityThreshold +
			", forceIrrigation=" + forceIrrigation +
			", forecastHours=" + forecastHours +
			'}';
	}
}
