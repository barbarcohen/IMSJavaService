package com.honeywell.ims.api.web;

import java.util.Date;

/**
 * Created by h134602 on 9/13/2016.
 */
public class Settings {

	private long id;

	private Date nextWatering;

	private long wateringDuration;//in seconds

	private int minHumidityTreshold;

	private boolean dummyWeatherMode;

	public int getMinHumidityTreshold() {
		return minHumidityTreshold;
	}

	public void setMinHumidityTreshold(final int minHumidityTreshold) {
		this.minHumidityTreshold = minHumidityTreshold;
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

	public boolean isDummyWeatherMode() {

		return dummyWeatherMode;
	}

	public void setDummyWeatherMode(final boolean dummyWeatherMode) {
		this.dummyWeatherMode = dummyWeatherMode;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}
}
